package movie.schema

import org.apache.spark.sql.types.{StringType, StructField, StructType}

object MovieSchemas {

  // Define the movie schema
  val moviesSchema = StructType(
    Seq(
      StructField("movieId", StringType),
      StructField("title", StringType),
      StructField("genre", StringType)
    )
  )

  // Define the ratings schema
  val ratingsSchema = StructType(
    Seq(
      StructField("userId", StringType),
      StructField("movieId", StringType),
      StructField("rating", StringType),
      StructField("timestamp", StringType)
    )
  )

  def getMovieSchema: StructType = {
    moviesSchema
  }

  def getRatingsSchema: StructType = {
    ratingsSchema
  }

}
