package org.jezzbox.footballpipeline.teams

import org.apache.spark.sql.functions.{col, explode, lit}
import io.delta.tables._
import org.jezzbox.footballpipeline.common.{AzureDataLake, FootyUtils}

object Main {
def main(args: Array[String]) {
  val fileDate = args(0)
  val filePath = AzureDataLake.getFilePath("teams", fileDate, ".json")
  val df = AzureDataLake.readJson(filePath)

  val responseCols = Array("team", "venue")
  val df2 = df.transform(FootyUtils.getResponseData(fileDate, responseCols))
  df2.show()

  val deltaPath = "silver/teams/teams-1"
  val deltaTableTeams1 = AzureDataLake.readDeltaTable(deltaPath)
  val dfUpdates = df2
  dfUpdates.show()

  val columnMap = FootyUtils.columnMapper(dfUpdates.columns, "updates")

  deltaTableTeams1
    .as("teams")
    .merge(
      dfUpdates.as("updates"),
      "teams.team.id = updates.team.id and teams.fileDate <= updates.fileDate")
    .whenMatched
    .updateExpr(
      columnMap)
    .whenNotMatched
    .insertExpr(
      columnMap)
    .execute()
  println("success!")
}
}
