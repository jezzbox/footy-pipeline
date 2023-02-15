ThisBuild / organization := "org.jezzbox"
ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.14"

lazy val footballpipeline = project
  .in(file("."))
  .aggregate(
    common,
    fixtures,
    teams
  )

lazy val common = project
  .settings(
    name := "common",
    idePackagePrefix := Some("org.jezzbox.footballpipeline.common"),
    libraryDependencies ++= commonDependencies
  )

lazy val fixtures = project
  .settings(
      name := "fixtures",
      idePackagePrefix := Some("org.jezzbox.footballpipeline.fixtures"),
      libraryDependencies ++= commonDependencies
  ).dependsOn(
  common
)

lazy val teams = project
  .settings(
    name := "teams",
    idePackagePrefix := Some("org.jezzbox.footballpipeline.teams"),
    libraryDependencies ++= commonDependencies
  ).dependsOn(
  common
)

val sparkVersion = "3.3.0"

lazy val dependencies =
    new {
        val hadoopV = "3.3.0"
        val sparkV = "3.3.0"
        val deltaV = "2.2.0"

        val sparkCore = "org.apache.spark" %% "spark-core" % sparkV withSources()
        val sparkSql = "org.apache.spark" %% "spark-sql" % sparkV withSources()
        val sparkHive = "org.apache.spark" %% "spark-hive" % sparkV withSources()
        val hadoopAzure = "org.apache.hadoop" % "hadoop-azure" % hadoopV withSources()
        val deltaCore = "io.delta" %% "delta-core" % deltaV
    }

lazy val commonDependencies = Seq(
    dependencies.sparkCore,
    dependencies.sparkSql,
    dependencies.sparkHive,
    dependencies.hadoopAzure,
    dependencies.deltaCore,
)

//lazy val fixtures = (project in file("."))
//  .settings(
//    name := "footballpipeline",
//    idePackagePrefix := Some("org.jezzbox.footballpipeline"),
//    libraryDependencies += "org.apache.spark" %% "spark-core" % sparkVersion withSources(),
//    libraryDependencies += "org.apache.spark" %% "spark-sql" % sparkVersion withSources(),
//    libraryDependencies += "org.apache.hadoop" % "hadoop-azure" % sparkVersion,
//    libraryDependencies += "io.delta" %% "delta-core" % "2.2.0",
//    libraryDependencies += "org.apache.spark" %% "spark-hive" % sparkVersion withSources()
//)