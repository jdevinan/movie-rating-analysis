package movie

import movie.schema._
import movie.utils.DataFrameUtils
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.scalatest.funsuite.AnyFunSuite

class MovieRatingAnalysisTest extends AnyFunSuite {

  // Create a local SparkSession for testing
  val spark: SparkSession = SparkSession.builder()
    .appName("DataFrameUtilsTest")
    .master("local[2]")
    .getOrCreate()

  import spark.implicits._

  test("Test readFileToDataFrame for Movies CSV file") {
    val inputPath = "src/test/resources/input/movies_test.csv"
    val fileFormat = "csv"
    val delimiter = "::"
    val schema = MovieSchemas.getMovieSchema

    val result = DataFrameUtils.readFileToDataFrame(spark, inputPath, fileFormat, delimiter, schema)
    println("============ Printing data from Movies CSV file ============")
    result.show()

    assert(result.columns === Array("movieId", "title", "genre"))
    assert(result.count() === 25) // Change this to match the number of rows in your test CSV file
  }

  test("Test readFileToDataFrame for Ratings CSV file format") {
    val inputPath = "src/test/resources/input/ratings_test.csv"
    val fileFormat = "csv"
    val delimiter = "::"
    val schema = MovieSchemas.getRatingsSchema

    val result = DataFrameUtils.readFileToDataFrame(spark, inputPath, fileFormat, delimiter, schema)
    println("============ Printing data from Ratings CSV file ============")
    result.show()

    assert(result.columns === Array("userId", "movieId", "rating", "timestamp"))
    assert(result.count() === 25) // Change this to match the number of rows in your test CSV file
  }

//  test("Test readFileToDataFrame for JSON file format") {
//    val inputPath = "src/test/resources/movies.json"
//    val fileFormat = "json"
//    val delimiter = ","
//    val schema = MovieSchemas.getMovieSchema
//
//    val result = DataFrameUtils.readFileToDataFrame(spark, inputPath, fileFormat, delimiter, schema)
//
//    assert(result.columns === Array("movieId", "title"))
//    assert(result.count() === 3) // Change this to match the number of rows in your test JSON file
//  }


  test("Test writeDataFrame - Movie Stats - Min, Max, Average Ratings of Movies") {
    val outputPath = "src/test/resources/output/movies_with_stats"
    val fileFormat = "parquet"

    // Mock ratings data
    val ratingsTestData = Seq(
      (1, 1, 4.5),
      (1, 2, 3.5),
      (2, 1, 5.0),
      (2, 3, 4.0)
    ).toDF("userId", "movieId", "rating")

    val movieRatingsStatsTestDF: DataFrame = DataFrameUtils.getMinMaxAvgOfMovie(ratingsTestData)

    DataFrameUtils.writeDataFrame(movieRatingsStatsTestDF, outputPath, fileFormat)

    val result = spark.read.format(fileFormat).load(outputPath)

    assert(result.columns === Array("movieId", "max", "min", "average"))
    assert(result.count() === 3)
  }


  test("Test writeDataFrame - Top3 Movies Per User") {
    val outputPath = "src/test/resources/output/top_3_movies_per_user"
    val fileFormat = "parquet"

    // Mock ratings data
    val top_3_movies_per_user_TestData = Seq(
      (1, 1, 4.5),
      (1, 2, 3.5),
      (2, 1, 5.0),
      (2, 3, 4.0)
    ).toDF("userId", "movieId", "rating")

    DataFrameUtils.writeDataFrame(top_3_movies_per_user_TestData, outputPath, fileFormat)

    val result = spark.read.format(fileFormat).load(outputPath)

    assert(result.columns === Array("userId", "movieId", "rating"))
    assert(result.count() === 4)
  }

  // Add test cases for other methods as needed
}
