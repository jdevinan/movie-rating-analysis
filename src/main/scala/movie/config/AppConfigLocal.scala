package movie.config

object AppConfigLocal {
  //    local[*] for running locally or spark://host:port for a cluster)
  val SPARK_MASTER_NODE_LOCAL: String = "local[2]"
  val MOVIES_APP_NAME: String = "Movie Rating Analysis"
  val MOVIES_INPUT_PATH: String = "data/input/movielens/movies/"
  val MOVIES_INPUT_FILE_FORMAT: String = "csv"
  val MOVIES_DELIMITER: String = "::"
  val MOVIES_OUTPUT_FILE_FORMAT: String = "parquet"
  val MOVIES_STATS_OUTPUT_PATH: String = "data/output/movies_with_stats"
  val MOVIES_TOP3_PER_USER_OUTPUT_PATH: String = "data/output/top_3_movies_per_user"

  val RATINGS_INPUT_PATH: String = "data/input/movielens/ratings/"
  val RATINGS_OUTPUT_PATH: String = "data/output/ratings"
  val RATINGS_INPUT_FILE_FORMAT: String = "csv"
  val RATINGS_DELIMITER: String = "::"
  val RATINGS_OUTPUT_FILE_FORMAT: String = "parquet"

  val USERS_INPUT_PATH: String = "data/input/movielens/users/"
  val USERS_OUTPUT_PATH: String = "data/output/user"
  val USERS_INPUT_FILE_FORMAT: String = "csv"
  val USERS_DELIMITER: String = "::"
  val USERS_OUTPUT_FILE_FORMAT: String = "parquet"
}
