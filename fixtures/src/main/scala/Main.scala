package org.jezzbox.footballpipeline.fixtures

import org.apache.spark.sql.functions.{col, explode, lit}
import io.delta.tables._
import org.jezzbox.footballpipeline.common.{AzureDataLake, FootyUtils}

object Main {
  def main(args: Array[String]) {
//    val fileDate = args(0)
//    val filePath = AzureDataLake.getFilePath("fixtures", fileDate, ".json")
//    val df = AzureDataLake.readJson(filePath)
//
//
//    val df2 = df.transform(FootyUtils.getResponseData(fileDate))
//
//    val df2 = df.select(explode(col("response")).alias("response"), col("fileDate"))
//    val df3 = df2.withColumn("events", col("response.events"))
//      .withColumn("fixture", col("response.fixture"))
//      .withColumn("goals", col("response.goals"))
//      .withColumn("league", col("response.league"))
//      .withColumn("score", col("response.score"))
//      .withColumn("teams", col("response.teams"))
//      .drop(col("response"))
//    df.show()
//    val deltaTableFixtures1 = DeltaTable.forPath(spark, "abfss://footy-pipeline@footballdatastore.dfs.core.windows.net/silver/fixtures/fixtures-1")
//    val dfUpdates = df3
//
//    deltaTableFixtures1
//      .as("fixtures")
//      .merge(
//        dfUpdates.as("updates"),
//        "fixtures.fixture.id = updates.fixture.id and fixtures.fileDate <= updates.fileDate")
//      .whenMatched
//      .updateExpr(
//        Map(
//          "fileDate" -> "updates.fileDate",
//          "events" -> "updates.events",
//          "fixture" -> "updates.fixture",
//          "goals" -> "updates.goals",
//          "league" -> "updates.league",
//          "score" -> "updates.score",
//          "teams" -> "updates.teams"
//        ))
//      .whenNotMatched
//      .insertExpr(
//        Map(
//          "fileDate" -> "updates.fileDate",
//          "events" -> "updates.events",
//          "fixture" -> "updates.fixture",
//          "goals" -> "updates.goals",
//          "league" -> "updates.league",
//          "score" -> "updates.score",
//          "teams" -> "updates.teams"
//        ))
//      .execute()
//    println("success!")
//  }
}}