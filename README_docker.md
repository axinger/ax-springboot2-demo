# 三要素

```text
镜像文件image,一个镜像可以创建多个容器
容器: 是用镜像创建的运行实例
仓库
```
安装命令如下：
```text
curl -fsSL https://get.docker.com | bash -s docker --mirror Aliyun
```
也可以使用国内 daocloud 一键安装命令：
```text
curl -sSL https://get.daocloud.io/docker | sh
```
## 镜像命令
```text
启动: systemctl start docker
停止: systemctl stop docker
重启: systemctl restart docker
查看状态: systemctl status docker
开机启动: systemctl enable docker
查看概要: docker info
查看本地镜像: docker images
拉取镜像: docker pull
查看存储: docker system df
强制删除镜像: docker rmi -f


```
## 容器命令
```text
启动交互式容器(前台命令行)

查看运行的容器: docker ps 
    停止后无法查看,要查看需要运行的,可以用 docker images 查找容器id 也可以用 docker ps -n 2 最近使用的
查看已经停止的: docker ps -n 2
进入容器: docker exec -it 容器id /bin/bash

退出
容器关闭:   exit 
容器不停止:  ctrl+p+q 

启动已经停止的容器id: docker start 容器id
重启容器: docker restart 容器id
停止容器: docker stop 容器id
强制停止容器: docker kill 容器id
删除已停止容器: docker rm

```

## 启动守护式容器
```text

启动守护容器: docker run -itd --name ax-mall-redis -p 6379:6379 redis

查看日志: docker logs 容器id

进入已经启动的容器:

    docker exec -it 容器id /bin/bash ,启动新进程,用exit退出,不会停止,常用
    docker attach 容器id ,用exit退出,会停止:   

```

## 复制文件到容器
```text
docker cp 容器di:容器内路径 目的主机路径
```


## 安装软件

### redis
```text

docker run -itd --name ax-mall-redis -p 6379:6379 redis

配置指定运行
#安装, 先提前建立配置文件redis.conf, 不然自动创建会识别成文件夹
docker run --privileged=true --name ax-mall-redis -p 6379:6379 -v /root/redis/redis.conf:/etc/redis/redis.conf -v /root/redis/data:/data -d redis redis-server /etc/redis/redis.conf

修改密码,直接修改 redis.conf 文件


===============
-p 6379:6379：映射容器服务的 6379 端口到宿主机的 6379 端口。外部可以直接通过宿主机ip:6379 访问到 Redis 的服务。

docker run -itd --name redis-test -p 6379:6379 redis

为现有的redis创建密码或修改密码的方法：

1.进入redis的容器 docker exec -it 容器id /bin/bash

2.运行命令：redis-cli

3.查看现有的redis密码：config get requirepass

4.设置redis密码 config set requirepass ****（****为你要设置的密码）

```
### 安装mysql
```text
docker run --privileged=true --name ax-mall-mysql -p 3306:3306 -v /root/mysql/log:/var/log/mysql -v /root/mysql/data:/etc/lib/mysql -v /root/mysql/conf:/etc/mysql/conf.d -e MYSQL_ROOT_PASSWORD=AXing#631122 -d mysql

 中文乱码,查看字符集
    show variables like 'char%';
    
中文乱码
vim my.cnf

[client]
default-character-set=utf8
[mysql]
default-character-set=utf8
[mysqld]
collation-server=utf8_general_ci
character-set-server=utf8
init-connect='SET NAMES utf8'
```

## 面试题
```text
仓库名,标签都是<none>的镜像,俗称 虚悬镜像, 需要删除
```

## 加载原理
```text
镜像层只读
容器层可写
```

## 容器数据卷
```text
--privileged=true

docker run -it --privileged=true-v 宿主机绝对路径目录:/容器内目录 镜像名(不是容器)

查看挂载等信息: docker inspect 

```
### 容器数据卷读写规则
```text
容器卷,读写权限,宿主机权限正常
```

### 容器数据卷继承
```text
docker run -it --privileged=true --volumes-from 父类 --name u2 Ubuntu
```

# 高级版

## mysql 主从复制,一主一从
```text
主
docker run --privileged=true --name ax-mall-mysql -p 3306:3306 -v /root/mysql/log:/var/log/mysql -v /root/mysql/data:/etc/lib/mysql -v /root/mysql/conf:/etc/mysql/conf.d -e MYSQL_ROOT_PASSWORD=AXing#631122 -d mysql

从
docker run --privileged=true --name ax-mall-mysql-slave -p 3308:3306 -v /root/mysql-slave/log:/var/log/mysql -v /root/mysql-slave/data:/etc/lib/mysql -v /root/mysql-slave/conf:/etc/mysql/conf.d -e MYSQL_ROOT_PASSWORD=AXing#631122 -d mysql


主创建从账户
CREATE USER 'slave'@'%' IDENTIFIED BY 'AXing#631122';

主授权 
GRANT REPLICATION SLAVE,REPLICATION CLIENT ON*.*TO 'slave'@'%'; 

查看主从信息
show master status;

在从mysql 添加

 #MASTER_LOG_FIL MASTER_LOG_POS 两个参数的值与主库保持一致,用 show master status; 查看 主信息 
change master to master_host='47.101.156.93',master_port=3306,master_user='slave',master_password='AXing#631122',master_log_file='binlog.000008',master_log_pos=156,master_connect_retry=30;

# 在从 查看 主 状态
show slave status \G;

Slave_IO_Running: No
Slave_SQL_Running: No 
还未开始

# 在  从 在中开启同步
start slave;

出现,就成功了 
Slave_IO_Running: Yes
Slave_SQL_Running: Yes 

# 绑定错误 先关闭线程 再绑定,再开启线程
STOP SLAVE IO_THREAD;
start SLAVE IO_THREAD; 

```

## 分布式存储 - 哈希取余算法
```text
面试题
1~2亿条数据需要缓存,如何设计这个存储案例

回答: 单机单台不可能,肯定是分布式存储,用redis如何落地?

3主3从redis集群扩容配置:

3种方案
1.哈希取余分区
    2亿条记录就是2亿个kv,单机不行必须分布式多机,假设有3台机器构成一个集群,用户每次读写操作都是根据公式 hash(key)%N个机器台数,计算出哈希值,用来决定数据映射到那个节点上
    
优点:
    简单粗暴,直接有效,只需要预估好数据规划好节点,例如3,8,10台,就能保证一段时间的数据支撑,使用hash算法让固定的一部分请求落到同一台服务器上,
这样每台服务器固定处理一部分请求(并维护这些请求的信息),起到负载均衡+分而治之的作用.

缺点:
    原来规划好的节点,进行扩容或者缩容比较麻烦,每次数据变动导致节点有变动,映射关心需要重新进行计算,在服务器个数固定不变没有问题,再扩容或者宕机情况,取模公式发生变化 hash(key)/N 发生变化,
    会导致hash取余全部数据重新洗牌
   
   小公司使用就够了
    
    
2.一致性哈希算法分区 (分布式缓存数据变动和映射问题,某个机器宕机了,分母数量改变了,自然取余数就不合适了)
    三个步骤
    1.算法构建一致性哈希环
    一致性哈希环比如有个hash函数并按照算法产生hash值,这个算法的所有可能哈希值会构成一个全量集,这个集合可以成为一个hash空间[0,2^32-1],这个是一个线性空间,
    但是在算法中,我们通过适当的逻辑控制将他首尾相连(0=2^32),这样让他的逻辑形成一个环形空间.
    
    2.服务器ip节点映射
    3.key落到服务器的落键规则
    
优点:
    一致性哈希算法的容错性和扩容性
缺点:
    数据倾斜问题
    
3.哈希槽分区
    哈希槽实质就是一个数组,数组[0,2^14-1]形成hash slot空间
    解决均匀分配的问题,
    一个集群只能有16384个槽,


集群存储错误:
    单机登录,set 值会报错, 添加参数登录集群模式 -c
    redis-cli -p 端口 -c 
    
    主宕机变从,修复后还是从
    
    
主从扩容,缩容
   
    重新分配槽位: redis-cli --cluster reshard ip:端口
 
    槽位分配说明: 
        扩容:槽位变更,每个槽位 前段 切除部分,给新增的
        缩容: 先移除从机,主机移除槽位,可以指定全部给某一个
       
```
# DockerFile
```text
构建镜像的文本

1.每条保留字指令必须为大写字母,且后面要跟随至少一个参数
2.指令按照从上往下,顺序执行
3.#表示注释
4.每条指令都会创建一个新的镜像层,并对镜像进行提交

 
```

