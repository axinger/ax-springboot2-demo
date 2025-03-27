
FROM eclipse-temurin:17-jdk-centos7

#作者
MAINTAINER 阿星

# volume 指定临时文件目录为/tmp, 在主机/var/lib/docker 目录下创建一个临时文件夹并链接到容器的/tmp
VOLUME /logs
VOLUME /data
#ENV APP_NAME=server.jar

#设置工作目录，设置了工作目录，它将会作用于该指令后面的 RUN, CMD, ENTRYPOINT, COPY , ADD 指令
#WORKDIR /app

# 安装lsof
#RUN apt-get update && apt-get install -y lsof


RUN bash -c 'mkdir -p {config,target}'
#RUN sh -c 'mkdir config target'

#将配置文件添加到容器中
COPY /docker/application.yml /target/application.yml
COPY /docker/bootstrap.yml /target/bootstrap.yml

#将本地文件添加到容器中
COPY /target/*.jar /target/server.jar

#配置容器，使其可执行化
# 自动识别config/application.yml

# 指定环境
#ENTRYPOINT java -jar -Dspring.profiles.active=prd /target/server.jar

# 不指定,用application.yml 控制
ENTRYPOINT java -Xms512m -Xmx512m -Xmn256m -jar /target/server.jar --spring.profiles.active=prd

#配置时区，不然会发现打包到docker中启动的容器日志里的时间是差8个小时的
#这条命令会先通过 cp 命令将主机上的 /usr/share/zoneinfo/Asia/Shanghai 文件复制到容器中的 /etc/localtime 路径上，
#从而使容器中的系统时区与主机保持一致。接着，echo 命令将 Asia/Shanghai 设置为默认的容器时区，以便在后续操作过程中使用。
#使用上海时区, GTM+8,不同系统可能存在差异
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone

# 暴露端口,需要和服务的端口一致
EXPOSE 12021

#在 Docker 中，无需（也不应）使用 nohup，因为：
#
#容器需要前台进程保持运行。
#
#日志应输出到标准输出。
#
#信号需正确传递给应用进程。
