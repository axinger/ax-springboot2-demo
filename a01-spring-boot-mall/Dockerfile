# 基础镜像使用java
FROM java:8

#作者
MAINTAINER 阿星

# volume 指定临时文件目录为/tmp, 在主机/var/lib/docker 目录下创建一个临时文件夹并链接到容器的/tmp
VOLUME /tmp

# 将jar包添加到容器中,并更名
ADD target/ax-mall-*.jar ax-mall.jar

# 运行jia 包
RUN bash -c 'touch /ax-mall.jar'
ENTRYPOINT ["java","-jar","/ax-mall.jar"]

# 暴露端口
EXPOSE 8090