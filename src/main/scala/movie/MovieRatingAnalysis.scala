package movie

import movie.config.AppConfigLocal
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions._
import movie.schema._
import movie.utils.DataFrameUtils

object MovieRatingAnalysis {
  def main(args: Array[String]): Unit = {

    val spark = DataFrameUtils.createSparkSession(AppConfigLocal.MOVIES_APP_NAME,
      AppConfigLocal.SPARK_MASTER_NODE_LOCAL)

    import spark.implicits._

    // Step 1: Read in movies.dat to Spark DataFrames
    val moviesDF: DataFrame = DataFrameUtils.getMoviesData(spark)

    println("============ Printing moviesDF - BEGIN ============")
    moviesDF.show()

    // Step 2: Read in ratings.dat to Spark DataFrames
    val ratingsDF: DataFrame = DataFrameUtils.getRatingsData(spark)
                              .select($"userId", $"movieId", $"rating".cast("double"))

    println("============ Printing ratingsDF - BEGIN ============")
    ratingsDF.show()

    // Step 3: Create a new DataFrame with max, min, and average rating for each movie
    val movieRatingsStatsDF: DataFrame = DataFrameUtils.getMinMaxAvgOfMovie(ratingsDF)

    val moviesWithStatsDF = moviesDF.join(movieRatingsStatsDF, Seq("movieId"), "left")

    // Step 4: Create a new DataFrame containing each user's top 3 movies based on their rating
    val windowSpec = Window.partitionBy("userId").orderBy($"rating".desc)
    val top3MoviesPerUserDF = ratingsDF.withColumn("rank", rank().over(windowSpec))
      .filter($"rank" <= 3)
      .drop("rank")

    print("============ Printing top3MoviesPerUserDF - BEGIN ============")
    top3MoviesPerUserDF.show()

    // Step 5: Write result DataFrames to output directory
    DataFrameUtils.writeMoviesDataWithStatsData(moviesWithStatsDF)
    DataFrameUtils.writeTop3MoviesPerUserData(top3MoviesPerUserDF)

    spark.stop()
  }

}
