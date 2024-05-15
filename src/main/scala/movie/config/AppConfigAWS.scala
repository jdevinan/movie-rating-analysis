package movie.config

object AppConfigAWS {
  //    local[*] for running locally or spark://host:port for a cluster)
  val SPARK_MASTER_NODE_LOCAL: String = "local[2]"

  val MOVIES_APP_NAME: String = "Movie Rating Analysis"
  val S3_MOVIES_INPUT_PATH: String = "s3://path-to-S3-location/data/input/movielens/movies/2024/05/14/movies.dat"
  val MOVIES_INPUT_FILE_FORMAT: String = "csv"
  val MOVIES_DELIMITER: String = "::"
  val MOVIES_OUTPUT_FILE_FORMAT: String = "parquet"
  val S3_MOVIES_STATS_OUTPUT_PATH: String = "s3://path-to-S3-location/data/output/movies_with_stats/2024/05/14"
  val S3_MOVIES_TOP3_PER_USER_OUTPUT_PATH: String = "s3://path-to-S3-location/data/output/top_3_movies_per_user/2024/05/14"

  val S3_RATINGS_INPUT_PATH: String = "s3://path-to-S3-location/data/input/movielens/ratings/2024/05/14/ratings.dat"
  val S3_RATINGS_TOP3_PER_USER_OUTPUT_PATH: String = "s3://path-to-S3-location/data/output/ratings/2024/05/14"
  val RATINGS_INPUT_FILE_FORMAT: String = "csv"
  val RATINGS_DELIMITER: String = "::"
  val RATINGS_OUTPUT_FILE_FORMAT: String = "parquet"

  val S3_USERS_INPUT_PATH: String = "s3://path-to-S3-location/data/input/movielens/user/2024/05/14/users.dat"
  val S3_USERS_TOP3_PER_USER_OUTPUT_PATH: String = "s3://path-to-S3-location/data/output/user/2024/05/14"
  val USERS_INPUT_FILE_FORMAT: String = "csv"
  val USERS_DELIMITER: String = "::"
  val USERS_OUTPUT_FILE_FORMAT: String = "parquet"
}
