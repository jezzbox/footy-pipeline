package org.jezzbox.footballpipeline.common

import org.apache.spark.sql.{DataFrame, Column}
import org.apache.spark.sql.functions.{col, lit, explode}

object FootyUtils {
  def withFileDate(fileName: String)(df: DataFrame): DataFrame = {
    df.withColumn("fileDate", lit(fileName.toLong))
  }

  def withExplodedResponse()(df: DataFrame): DataFrame = {
    df.withColumn("response", explode(col("response")))
  }

  def flattenResponse(cols: Array[String])(df: DataFrame) : DataFrame = {
    val colNames = cols.map(name => col("response." + name))
    df.select(colNames:_*)
  }

  def getResponseData(fileName: String, responseCols: Array[String])(df: DataFrame) : DataFrame = {
    df.transform(withExplodedResponse())
      .transform(flattenResponse(responseCols))
      .transform(withFileDate(fileName))
  }

  def columnMapper(columns: Array[String], sourceTable: String): scala.collection.mutable.Map[String, String] = {
    val columnMap = scala.collection.mutable.Map[String, String]()
    for (mapItem <- columns) {
      val mapValue = sourceTable + "." + mapItem
      columnMap += (mapItem -> mapValue)
    }
    columnMap
  }
}
