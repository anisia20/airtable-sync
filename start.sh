#/bin/bash
cd /home/ec2-user/docker/signal-batch
docker rmi signal/signal-batch:latest
#rm -rf .gradle
#rm -rf build
export JAVA_HOME="/usr/lib/jvm/java-11-amazon-corretto"
git pull
./gradlew build -x test -Dquarkus.profile=prod -Dquarkus.package.type=native 
docker build -f src/main/docker/Dockerfile.jvm -t signal/signal-batch .
docker run -v /home/ec2-user/docker/signal-batch/vec:/vec -p 8201:8080 --name signal-batch -i -d --rm signal/signal-batch
#docker run -p 8201:8080 --name signal-batch -i -d --rm signal/signal-batch
