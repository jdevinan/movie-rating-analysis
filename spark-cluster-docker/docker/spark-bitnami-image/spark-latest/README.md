## Building Spark Cluster with latest Bitnami Spark Docker Images with docker-compose
Creating Spark Cluster with latest bitnami image.

### Pre-requisite SW installs
* Ensure the sure you have Docker and Docker Compose installed, and then you can start the Spark cluster using docker-compose up -d.

### About docker-compose file
* We define three services: spark-master, spark-worker-1 and spark-worker-2 using the bitnami/spark:latest image for building local Spark Cluster.
* The spark-master service serves as the Spark Master, and the Workers (spark-worker-1 and spark-worker-2) are configured to connect to it.
* Each service has its hostname, port mappings, CPU and memory limits specified in the deploy section, and is connected to a custom network spark-net with IP addresses from the specified subnet.
* Adjust the CPU and memory limits as needed based on your requirements.

### Run the docker-compose
```sh
docker-compose up -d 
```