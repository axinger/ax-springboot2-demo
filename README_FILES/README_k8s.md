# 介绍和特性

```text
1 基于集群部署工具kubeadm
2.基于二进制包方式

k8s目标实施让部署容器化应用更加简单


1.自动装箱
    按照要求自动部署
2.自我修复,水平扩展
3.服务发现,负载均衡
4.滚动更新
5.版本回退
6.秘钥和配置管理
    类似热部署
7.储存编排
8.批量处理

```

```text
组件
Master 主控节点,node 工作节点
master组件
    api server: 集群统一入口,
    scheduler: 节点调度
    Controller-manager: 处理集群中常规后台任务,一个资源对应一个控制器
    etcd: 存储
    
worker node 组件
    kubelet: master派到node节点代表,管理本机容器
    kube-Proxy: 网络代理,实现负载均衡等操作

```

# k8s 核心概念

```text
Pod:
    最小部署单元;
    一组容器的集合;
    共享网络
    短暂生命周期,
    
Controller
    确保预期的pod副本数量,
    无状态应用部署:
    有状态应用部署:
    确保所有的node运行同一个pod
    一次性任务和定时任务
    
Service
    定义一组pod的访问规则
```

# 部署规划

## 平台规划

```text
平台: 单master集群,
     多master集群
     
硬件:
测试环境
    master:  双核,4G 20G
    node: 4核, 8G 40G 
  
生产环境
    更高要求
```
