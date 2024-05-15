package movie.utils

import movie.config.AppConfigLocal
import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.StructType
import movie.schema.MovieSchemas

object DataFrameUtils {

  def createSparkSession(appName: String, masterNode: String): SparkSession = {
    //    local[*] for running locally or spark://host:port for a cluster)
    SparkSession.builder()
      .appName(appName)
      .master(masterNode)
      .getOrCreate()
  }

  def readFileToDataFrame(spark: SparkSession, inputPath: String, fileFormat: String, fileDelimiter: String, schema: StructType): DataFrame = {
    fileFormat match {
      case "csv" =>
        spark.read
          .format(fileFormat)
          .option("delimiter", fileDelimiter)
          .schema(schema)
          .load(inputPath)
      case "json" =>
        spark.read
          .format(fileFormat)
          .option("delimiter", fileDelimiter)
          .schema(schema)
          .load(inputPath)
      // Add more cases for other file formats if needed
      case _ =>
        throw new IllegalArgumentException(s"Unsupported file format: $fileFormat")
    }
  }

  def writeDataFrame(df: DataFrame, outputPath: String, fileFormat: String): Unit = {
    try {
      df.write
        .mode(SaveMode.Overwrite) // Choose the save mode: Overwrite, Append, Ignore, ErrorIfExists
        .format(fileFormat) // Specify the file format: "parquet", "csv", "json", etc.
//        .option("header", "true") // Specify if the header should be written (if applicable)
        .save(outputPath)
    } catch {
      case e: Exception =>
        println(s"Error writing DataFrame to $outputPath: ${e.getMessage}")
    }
  }

  // Step 1: Read in movies.dat to Spark DataFrames
  def getMoviesData(spark: SparkSession): DataFrame = {
    val moviesDF: DataFrame = DataFrameUtils.readFileToDataFrame(spark,
      AppConfigLocal.MOVIES_INPUT_PATH,
      AppConfigLocal.MOVIES_INPUT_FILE_FORMAT,
      AppConfigLocal.MOVIES_DELIMITER,
      MovieSchemas.getMovieSchema)
    moviesDF
  }

  // Step 2: Read in ratings.dat to Spark DataFrames
  def getRatingsData(spark: SparkSession): DataFrame = {
    val ratingsDF: DataFrame = DataFrameUtils.readFileToDataFrame(spark,
      AppConfigLocal.RATINGS_INPUT_PATH,
      AppConfigLocal.RATINGS_INPUT_FILE_FORMAT,
      AppConfigLocal.RATINGS_DELIMITER,
      MovieSchemas.getRatingsSchema)
    ratingsDF
  }

  def writeTop3MoviesPerUserData(top3MoviesPerUserDF: DataFrame): Unit = {
    DataFrameUtils.writeDataFrame(top3MoviesPerUserDF,
      AppConfigLocal.MOVIES_TOP3_PER_USER_OUTPUT_PATH,
      AppConfigLocal.MOVIES_OUTPUT_FILE_FORMAT)
  }

  // Step 4: Write out the original and new DataFrames
  def writeMoviesDataWithStatsData(moviesWithStatsDF: DataFrame): Unit = {
    DataFrameUtils.writeDataFrame(moviesWithStatsDF,
      AppConfigLocal.MOVIES_STATS_OUTPUT_PATH,
      AppConfigLocal.MOVIES_OUTPUT_FILE_FORMAT)
  }

  // Step 3: Create a new DataFrame with max, min, and average rating for each movie
  def getMinMaxAvgOfMovie(ratingsDF: DataFrame): DataFrame = {
    val movieRatingsStatsDF = ratingsDF.groupBy("movieId")
      .agg(
        max("rating").alias("max"),
        min("rating").alias("min"),
        avg("rating").alias("average")
      )
    movieRatingsStatsDF
  }
}
