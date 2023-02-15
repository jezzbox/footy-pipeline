package org.jezzbox.footballpipeline.common

import org.apache.spark.sql.{DataFrame, Column}
import io.delta.tables.DeltaTable

object AzureDataLake extends SparkSessionWrapper {
  private lazy val rawStorageAccount = "apifootball@footballdatastore.dfs.core.windows.net"
  private lazy val silverStorageAccount = "footy-pipeline@footballdatastore.dfs.core.windows.net"
  private lazy val prefix = "abfss://"

  def readJson(path: String): DataFrame = {
    val fileName = prefix + rawStorageAccount + "/" + path
    spark.read.format("json").load(fileName)
  }

  def getFilePath(path: String, fileName: String, fileSuffix: String): String = {
    path + "/" + fileName + fileSuffix
  }

  def readDeltaTable(path: String): DeltaTable = {
    val fullPath = prefix + silverStorageAccount + "/" + path
    DeltaTable.forPath(spark, fullPath)
  }
}
