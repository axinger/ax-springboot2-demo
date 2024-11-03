# springboot笔记

```text
springboot及spring cloud的demo
```

[docker](./README_FILES/README_docker.md)

[mysql](./README_FILES/README_msyql.md)

[rabbitMQ](./README_FILES/README_rabbitMQ.md)

[springboot](./README_FILES/README_springboot)


# 高并发
```
高并发系统保护的三把利器 : 缓存、降级和限流
```
```angular2html
限流方式
    限制总并发数(比如数据库连接池、线程池)
    限制瞬时并发数(如nginx的limit_conn模块，用来限制瞬时并发连接数)
    限制时间窗口内的平均速率(如Guava的RateLimiter、nginx的limit_req模块，限制每秒的平均速率)
    限制远程接口调用速率
    限制MQ的消费速率。
    可以根据网络连接数、网络流量、CPU或内存负载等来限流

常见的限流算法有：漏桶(Leaky Bucket)算法、令牌桶算法(Token Bucket)，计数器也可以进行粗暴限流实现
```
