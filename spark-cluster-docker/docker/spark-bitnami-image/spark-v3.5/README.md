## Building Spark Cluster with Bitnami Spark Docker Images version-3.5 with docker-compose
Creating Spark Cluster with version-3.5 bitnami spark docker image.

### Pre-requisite SW installs
* Ensure the sure you have Docker and Docker Compose installed, and then you can start the Spark cluster using docker-compose up -d.

### About docker-compose file
* We define three services: spark-master, spark-worker-1 and spark-worker-2 using the  bitnami/spark:3.5 image for building local Spark Cluster.
* The spark-master service serves as the Spark Master, and the Workers (spark-worker-1 and spark-worker-2) are configured to connect to it.
* Each service has its hostname, port mappings, CPU and memory limits specified in the deploy section, and is connected to a custom network spark-net with IP addresses from the specified subnet.
* Adjust the CPU and memory limits as needed based on your requirements.

### Run the docker-compose
```sh
Go to this directory i our project structure:
cd movie-rating-analysis/spark-cluster-docker/docker/spark-bitnami-image/spark-v3.5

Launch Single Node Spark Cluster by running below command:
  docker-compose up -d 

Find Spark Master Node name by running below command:
  docker ps  

Verify App Jar files and Data directories are mounted correctly:
docker exec -it spark-master35  ls -l /opt/spark-apps
docker exec -it spark-master35  ls -l /opt/bitnami/spark/data/input/movielens/
  
Connect to Spark Master Node:
  docker exec -it spark-master35  /bin/bash

Launch spark-submit command on  Spark Master Node:
  /opt/bitnami/spark/bin/spark-submit --master spark://spark-master35:7077 --class movie.MovieRatingAnalysis /opt/spark-apps/movie-rating-analysis_2.12-1.0.jar --driver-memory 3g --executor-memory 3g --num-executors 2 --executor-cores 2 --conf spark.dynamicAllocation.enabled=false

Verify the output after successful excecution of Spark App in below directories on Spark Master Node:
 ls -l /opt/bitnami/spark/data/output/movies_with_stats
 ls -l  /opt/bitnami/spark/data/output/top_3_movies_per_user

```