name := "movie-rating-analysis"
version := "1.0"

//scalaVersion := "2.12.15"
//val sparkVersion = "3.1.1"

scalaVersion := "2.12.17"
val sparkVersion = "3.5.0"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,
  "org.scalatest" %% "scalatest" % "3.2.9" % "test"
)

//Scala - 2.12.17
//Spark - 3.5.0

////name of the package
//name := "movie-rating-analysis"
////version of our package
//version := "1.0"
////version of Scala
//scalaVersion := "2.13"
//
//// spark library dependencies
//libraryDependencies ++= Seq(
//  "org.apache.spark" %% "spark-core" % "3.3.0",
//  "org.apache.spark" %% "spark-sql"  % "3.3.0",
//  "org.scalatest" %% "scalatest" % "3.2.16" % Test
//)