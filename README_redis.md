## 安装

```shell
1、检查安装依赖程序

yum install gcc-c++
yum install -y tcl
yum install wget
2、获取安装文件

wget http://download.redis.io/releases/redis-2.8.13.tar.gz
3、解压文件

tar -xzvf redis-2.8.19.tar.gz
mv redis-2.8.19 /usr/local/redis
4、进入目录

cd /usr/local/redis
5、编译安装
make
make install

6、设置配置文件路径
mkdir -p /etc/redis
cp redis.conf /etc/redis

7、修改配置文件

vi /etc/redis/redis.conf
仅修改： daemonize yes （no-->yes）

8、启动
/usr/local/bin/redis-server /etc/redis/redis.conf

9、查看启动
ps -ef | grep redis 

10、使用客户端
redis-cli
>set name david
OK
>get name
"david"

11.关闭客户端
redis-cli shutdown

12、开机启动配置

echo "/usr/local/bin/redis-server /etc/redis/redis.conf &" >> /etc/rc.local
开机启动要配置在 rc.local 中，而 /etc/profile 文件，要有用户登录了，才会被执行。
```

```text
Redis支持五种数据类型：string（字符串），hash（哈希），list（列表），set（集合）及zset(sorted set：有序集合)。
一、String（字符串）

    string 是 redis 最基本的类型，你可以理解成与 Memcached 一模一样的类型，一个 key 对应一个 value。

    string 类型是二进制安全的。意思是 redis 的 string 可以包含任何数据。比如jpg图片或者序列化的对象。

    string 类型是 Redis 最基本的数据类型，string 类型的值最大能存储 512MB。

二、Hash（哈希）

    Redis hash 是一个键值(key=>value)对集合。

    Redis hash 是一个 string 类型的 field 和 value 的映射表，hash 特别适合用于存储对象。

    每个 hash 可以存储 232 -1 键值对（40多亿）

三、List（列表）

    Redis 列表是简单的字符串列表，按照插入顺序排序。你可以添加一个元素到列表的头部（左边）或者尾部（右边）。
    列表最多可存储 232 - 1 元素 (4294967295, 每个列表可存储40多亿)。
    他的底层是一个链表

四、Set（集合）

    Redis的Set是string类型的无序集合。

    集合是通过哈希表实现的，所以添加，删除，查找的复杂度都是O(1)。

    集合中最大的成员数为 232 - 1(4294967295, 每个集合可存储40多亿个成员)。

    sadd key member
    添加一个 string 元素到 key 对应的 set 集合中，成功返回1，如果元素已经在集合中返回 0，如果 key 对应的 set 不存在则返回错误。
注意：以上实例中 beijing添加了两次，但根据集合内元素的唯一性，第二次插入的元素将被忽略。

五、zset(sorted set：有序集合)

    zset 和 set 一样也是string类型元素的集合,且不允许重复的成员。
    不同的是每个元素都会关联一个double类型的分数。redis正是通过分数来为集合中的成员进行从小到大的排序。

    zset的成员是唯一的,但分数(score)却可以重复。

```

## 事务

```text
redis事务是一个单独的隔离操作:事务中的所有命令都会序列化,按顺序的执行,
事务在执行过程中,不会被其他客户端发送来的命令请求打断.
主要作用:就是 串联多个命令 防止别的命令插队(秒杀)

开启事务: multi
提交事务:exec
取消:discard

三特性:
1.单独的隔离操作:所有命令都会系列化,an顺序执行,执行过程中,不会被其他客户端命令打断;
2.没有隔离级别的概念:队列中的命令没有提交之前都不会实际执行;
3.不保证原子性:假如有1条命令失败,其他命令仍然执行,没有回滚;
```

```text
组队阶段 > 执行阶段
错误: 
组队阶段错误,都会取消,不执行
执行阶段错误:只取消错的
```

```text
悲观锁: 每次都加锁
乐观锁: 加版本号,用于多度的应用,提高吞吐量;

watch :监听,发现被监听值被修改,就取消操作
```

## 持久化,写入磁盘

```text
RDB: redis data base
默认开启
写实复制技术,建立临时表,进行替换,吞吐量大,完整性一致性要求不高
缺点:最后一次持久化后的数据可能丢失

AOF: 文件追加
记录每个写操作,默认不开启

同时开启,优先AOF

```

## 主从复制

```text
master: 写为主
slave: 读为主

读写分离,容灾快速恢复

一主两从特点

从:挂了,重启,从需要再次手动加入主,从头复制主数据
主:挂了,重启,从还是从,主还是主,

主:发生变化时,主动同步给从,而不是从去同步
```

### 薪火相传

```text
主 > 从 > 从
从再同步到从中
```

### 反客为主

```text
主挂了,从升级为主
slaveof no one,
缺点:需要手动设置

哨兵模式:
自动升级主,根据投票数
缺点: 复制延迟

```

## 集群

```text
容量
并发写入
3.0开始 无中心化集群配置
```

## 三个典型问题

### 缓存穿透

```text
现象:
redis没有数据或者非法URL请求大量访问数据库,数据库奔溃;
解决:
1.对空值缓存,设置过期时间很短;
2.设置访问名单,白名单,使用bitmaps类型定义一个访问名单,效率低;
3.布隆过滤器,底层也是bitmaps;
4.进行实时监控;
   
```

### 缓存击穿-某个key过期

```text
现象:
1.数据库访问瞬时压力大;
2.redis没有出现key大量过期,
3,redis正常
原因:
1.redis某个key过期,
解决:
1.预先设置热门数据,提前数据加入redis中
2.实时调整,调整key的过期时长
3.使用锁
```

### 缓存雪崩-大量key过期

```text
现象:
1.数据库压力变大,造成服务器奔溃
原因:
1.极少时间段,查询大量的key集中过期情况;
解决:
1.构建多层缓存架构, Nginx缓存+redis缓存+其他缓存(ehcache等)
2.使用锁或者队列,不适用高并发情况;
3.设置过期标准更新缓存,当key快要过期,提前通知另外的县城更新key缓存;
4.将缓存失效时间分散开;

```

## 分布式锁

```text
SETNX 命令

1.setnx k v 返回1 表示成功 返回0表示不能添加修改值,需要释放锁
2.del k 释放锁
3.锁需要设置过期时间
4.上锁之后,异常,还未设置过期时间;一边上锁,同时设置过期时间,命令: set user 10 nx ex 12 


误删锁
1.a 服务器卡顿,锁过期,被b拿到
2.a 又恢复了,会执行删除锁,就会把b的锁误删
使用UUID 解决
释放锁,判断uuid释放和加锁的时候一样的

保证原子性
1.  a 在删除锁时候,比较了uuid,,正要删除,还没有删除时候,锁到了过期时间,自动释放锁;
2. b 获取到锁, 因为a已经比较了uuid, a就会又删除b锁,缺乏原子性(本人操作不会被打算,就是原子性)

使用LUA脚本
```

## 6.0 新功能

```text
ACL: 访问控制列表,对用户进行更细粒度的权限控制;

```