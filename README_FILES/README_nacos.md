
```text
docker pull nacos/nacos-server:1.4.0

mkdir -p ~/docker/work/nacos/standalone-logs ~/docker/work/nacos/init.d ~/docker/work/nacos/conf


docker run -d \
-e MODE=standalone \
-e PREFER_HOST_MODE=hostname \
-e SPRING_DATASOURCE_PLATFORM=mysql \
-e MYSQL_SERVICE_HOST=localhost \
-e MYSQL_SERVICE_PORT=3306 \
-e MYSQL_SERVICE_USER=root \
-e MYSQL_SERVICE_PASSWORD=12345678 \
-e MYSQL_SERVICE_DB_NAME=nacos_config \
-e MYSQL_SERVICE_DB_PARAM="autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false&serverTimezone=Asia/Shanghai" \
-p 8848:8848 \
--restart=always \
--name nacos1.4.0 \
-v ~/docker/work/nacos/standalone-logs/:/home/nacos/logs \
-v ~/docker/work/nacos/init.d/:/home/nacos/init.d \
-v ~/docker/work/nacos/conf/:/home/nacos/conf \
nacos/nacos-server:1.4.0


低版本(1.4.x)挂载的文是/home/nacos/init.d/custom.properties
而2.0.3版本挂载的文件是/home/nacos/conf/application.properties


docker  run \
--name demo-nacos1.4 -d \
-p 8848:8848 \
--privileged=true \
--restart=always \
-e JVM_XMS=256m \
-e JVM_XMX=256m \
-e MODE=standalone \
-e PREFER_HOST_MODE=hostname \
-v ~/docker/work/nacos/logs:/home/nacos/logs \
-v ~/docker/work/nacos/init.d/custom.properties:/home/nacos/init.d/custom.properties \
nacos/nacos-server:1.4.0


docker run --name demo-nacos -e MODE=standalone -p 8848:8848 -d nacos/nacos-server:2.0.2

```
