# 基础镜像使用java
FROM openjdk:8-jdk

VOLUME /tmp

#将本地文件添加到容器中，不会自动解压文件，也不能访问网络资源
#COPY target/a16-spring-boot-mybatis-plus-2.0.5.jar mybatis-demo.jar
ADD a16-spring-boot-mybatis-plus-2.0.5.jar mybatis-demo.jar
#配置容器，使其可执行化。 我的理解就是拼接启动命令
ENTRYPOINT ["java","-jar","/mybatis-demo.jar"]

#配置时区，不然会发现打包到docker中启动的容器日志里的时间是差8个小时的
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
&& echo 'Asia/Shanghai' >/etc/timezone

# 暴露端口,需要和服务的端口一致
EXPOSE 8080


#
#docker run -v /somewhere/on/my/host/app.properties:/opt/app.properties my_image_name
#
#java -jar my_sb_app.jar --spring.config.location=/opt/app.properties
