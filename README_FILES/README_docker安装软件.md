# mysql
```text
docker run \
--name demo-mysql8 -d \
-p 3306:3306 \
--network demo-network \
-e MYSQL_ROOT_PASSWORD=12345678 \
-v $(pwd)/mydata/mysql/data:/var/lib/mysql \
-v $(pwd)/mydata/mysql/log:/var/log/mysql \
-v $(pwd)/mydata/mysql/conf/my.cnf:/etc/mysql/my.cnf \
--restart=always \
 mysql:8.0.28
```
## my.cnf
```text
[mysqld]
pid-file        = /var/run/mysqld/mysqld.pid
socket          = /var/run/mysqld/mysqld.sock
datadir         = /var/lib/mysql
secure-file-priv= NULL
# Disabling symbolic-links is recommended to prevent assorted security risks
symbolic-links=0

# Custom config should go here
!includedir /etc/mysql/conf.d/
# 默认使用“mysql_native_password”插件认证
default_authentication_plugin= mysql_native_password
#Accept connections from any IP address,客户端远程
bind-address = 0.0.0.0


[mysql]
# 设置mysql客户端默认字符集
default-character-set=utf8
[client]
# 设置mysql客户端连接服务端时默认使用的端口
port=3306
default-character-set=utf8
```

---

# nacos
## 低版本(1.4.x)挂载的文是/home/nacos/init.d/custom.properties
```text
docker pull nacos/nacos-server:1.4.0

mkdir -p ~/mydata/nacos1/{standalone-logs,init.d,conf}

docker run --name demo-nacos2.0.2 -d \
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
-v ~/mydata/nacos/standalone-logs/:/home/nacos/logs \
-v ~/mydata/nacos/init.d/:/home/nacos/init.d \
-v ~/mydata/nacos/conf/:/home/nacos/conf \
nacos/nacos-server:v2.0.2
```

## 2.0.3版本挂载的文件是/home/nacos/conf/application.properties
```text
mkdir -p ~/mydata/nacos1/{logs,conf}

docker  run \
--name demo-nacos2.0.4 -d \
-p 8848:8848 \
--network demo-network \
--privileged=true \
--restart=always \
-e MODE=standalone \
-e PREFER_HOST_MODE=hostname \
-v $(pwd)/mydata/nacos/logs:/home/nacos/logs \
-v $(pwd)/mydata/nacos/conf/application.properties:/home/nacos/conf/application.properties \
nacos/nacos-server:v2.0.4
```
## mysql.sql
```text
https://github.com/alibaba/nacos/blob/master/distribution/conf/nacos-mysql.sql
```
---

# seata
```text


docker run --name demo-seata -d \
--network demo-network \
-e SEATA_PORT=8091 \
-v $(pwd)/mydata/seata/conf/registry.conf:/seata-server/resources/registry.conf \
-v $(pwd)/mydata/seata/logs:/root/logs \
--privileged=true \
-p 8091:8091 \
seataio/seata-server:1.4.2

```
##
```text
新版本 seata+nacos 需要在nacos导入配置文件,官网执行sh脚本
```
# redis
```text
docker run --name demo-redis -d \
-p 6379:6379 \
--network demo-network \
-v $(pwd)/mydata/redis/redis.conf:/etc/redis.conf \
-v $(pwd)/mydata/redis/data:/data \
redis:6.2.6-alpine3.15 \
```



## nginx

```text
建立挂载目录
mkdir -p $(pwd)/mydata/nginx/{conf,conf.d,html,log}

docker run --name nginx-test -p 8080:80 -d nginx

docker cp bfd33ad90560:/etc/nginx/nginx.conf $(pwd)/mydata/nginx/conf/nginx.conf

docker cp bfd33ad90560:/etc/nginx/conf.d/default.conf $(pwd)/mydata/nginx/conf.d/default.conf


docker run --name demo-nginx -d \
-p 80:80 \
-v $(pwd)/mydata/nginx/html:/usr/share/nginx/html \
-v $(pwd)/mydata/nginx/conf/nginx.conf:/etc/nginx/nginx.conf \
-v $(pwd)/mydata/nginx/conf.d/default.conf:/etc/nginx/conf.d/default.conf \
-v $(pwd)/mydata/nginx/log:/var/log/nginx \
nginx \

```
