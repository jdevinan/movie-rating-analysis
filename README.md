# MovieRatingAnalysis Applicatiomn is built using Apache Spark Scala API

### Given Coding Task - Requirement
####  Introduction
This homework is intended to understand your capability in Spark and Scala/Python.
The problem is posed quite loosely to allow you to solve it in the way you are most comfortable. 
We will be looking at the approach used, the code style, structure, and quality as well as testing.
####  Data
Download the movie lens open data set (ml-1m.zip) from
http://files.grouplens.org/datasets/movielens/ml-1m.zip
####  Brief
The job needs to do the following:
1. Read in movies.dat and ratings.dat to spark dataframes.
2. Creates a new dataframe, which contains the movies data and 3 new columns max, min and
   average rating for that movie from the ratings data.
3. Create a new dataframe which contains each user’s (userId in the ratings data) top 3 movies
   based on their rating.
4. Write out the original and new dataframes in an efficient format of your choice.
   You should also include the command to build and run your code with spark-submit.

### Developed for versions to run on AWS EMR:
- Spark - 3.5.0 used by [AWS EMR-7.0.0](https://docs.aws.amazon.com/emr/latest/ReleaseGuide/emr-710-release.html)
- Scala - 2.12.17, sbt 1.10.0 and Java JDK 11 since supported by [Spark 3.5.0](https://spark.apache.org/docs/3.5.0/index.html)

Source Data downloaded from this location http://files.grouplens.org/datasets/movielens/ml-1m.zip
Unzip and place under the project structure here: 

* /Users/jdevinani/Downloads/Newday/movie-rating-analysis/data/input/movielens
* /Users/jdevinani/Downloads/Newday/movie-rating-analysis/spark-cluster-docker/docker/spark-bitnami-image/spark-v3.5/data/input/movielens:
* NOTE: Kept truncated ratings.dat file to make it easy to push to Git Repos. Remaining 2 input files (movies.dat, users.dat) as is.

### How to build the package
 1. sbt clean package
 2. mkdir jars
 3. cp ../movie-rating-analysis/target/scala-2.12/movie-rating-analysis_2.12-1.0.jar jars/movie-rating-analysis_2.12-1.0.jar 

### How to run the MovieRatingAnalysis  on Intellij IDEA
To run the Scala code for MovieRatingAnalysis examples:
 * Right click on the MovieRatingAnalysis class and Run the Spark App
 * View the Spark App execution from Spark WebUI at: http://jagans-mbp:4040/jobs/![Spark Cluster runing on Docker Desktop.png](..%2F..%2F..%2FDesktop%2FSpark%20Cluster%20runing%20on%20Docker%20Desktop.png)
   ![SparkUI-Local.png](..%2F..%2F..%2FDesktop%2FSparkUI-Local.png)

### Run locally on MacBookPro with Spark 3.5.0 and Scala Version "2.12.17"

###### Go to this READFME file for instructions on how to Spark Application on Docker based Spark Cluster: 
[movie-rating-analysis/spark-cluster-docker/docker/spark-bitnami-image/spark-v3.5/README.md](/Users/jdevinani/Downloads/Newday/movie-rating-analysis/spark-cluster-docker/docker/spark-bitnami-image/spark-v3.5/README.md)

######  Spark Cluster running on Dcoker Desktop:
![Spark Cluster runing on Docker Desktop.png](..%2F..%2F..%2FDesktop%2FSpark%20Cluster%20runing%20on%20Docker%20Desktop.png)

###### View the Spark App execution from Spark WebUI at:
![SparkUI of Spark Cluster runing on Docker.png](..%2F..%2F..%2FDesktop%2FSparkUI%20of%20Spark%20Cluster%20runing%20on%20Docker.png)