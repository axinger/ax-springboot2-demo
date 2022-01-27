```text

corePoolSize  ---------------------> 核心线程数

maximumPoolSize ---------------> 最大线程数

keepAliveTime --------------------> 当线程数大于核心时，此为终止前多余的空闲线程等待新任务的最长时间

unit -----------------------------------> 时间单位

workQueue ------------------------> 用于存储工作工人的队列

threadFactory ---------------------> 创建线程的工厂

handler ------------------------------> 由于超出线程范围和队列容量而使执行被阻塞时所使用的处理程序

       public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler)
                              
```
## 拒绝策略 4种
```text

https://www.jianshu.com/p/bf6fabb7b459

AbortPolicy 中止策略
默认的拒绝策略。直接抛出 java.util.concurrent.RejectedExecutionException异常
new ThreadPoolExecutor.AbortPolicy()
 
CallerRunsPolicy 抛弃策略
将任务返还给调用者线程执行
new ThreadPoolExecutor.CallerRunsPolicy()


DiscardPolicy 调用者运行
直接抛弃无法处理的任务，不予处理不抛异常。如果业务汇总允许任务丢失，这是最好的策略
new ThreadPoolExecutor.DiscardPolicy()

DiscardOldestPolicy 抛弃旧任务策略
抛弃队列中等待最久的任务，然后把当前任务加入队列中尝试再次提交当前任务
new ThreadPoolExecutor.DiscardOldestPolicy()

```

## 队列
```text
1 无界队列
2 有界队列
3 同步移交

在这里，我们主要对 ArrayBlockingQueue，PriorityBlockingQueue，DelayQueue，SynchronousQueue，LinkedTransferQueue，LinkedBlockingDeque的使用方法和应用场景做一个补充。

ArrayBlockingQueue：基于数组实现的阻塞队列，先进先出队列，有界队列。在创建时必须制定容量大小。并可以指定公平性与非公平性，默认情况下是非公平的，即不保证等待时间最长的队列最优先能够访问队列。
LinkedBlockingQueue：基于链表实现的阻塞队列，先进先出队列，有界队列。在创建时如果不指定容量大小，则默认大小为Integer.MAX_VALUE。
PriorityBlockingQueue：按照元素的优先级对元素进行排序，按照优先级顺序出队。并且该阻塞队列为无界阻塞队列，即容量没有上限（源码中它没有容器满的信号标志）。
DelayQueue：基于PriorityQueue的延时阻塞队列，无界队列。DelayQueue中的元素只有当其指定的延迟时间到了，才能够从队列中获取到该元素。因为DelayQueue是一个无界队列，所以往队列中插入数据的操作永远不会被阻塞，而只有获取数据的操作才会被阻塞。
SynchronousQueue：一个不存储元素的阻塞队列。
LinkedTransferQueue：一个由链表结构组成的无界阻塞队列。
LinkedBlockingDeque：一个由链表结构组成的双向阻塞队列。

```

```text
java线程池如何合理的设置大小 线程池究竟设置多大要看你的线程池执行的什么任务了，CPU密集型、IO密集型、混合型，任务类型不
同，设置的方式也不一样
任务一般分为:CPU密集型、IO密集型、混合型，对于不同类型的任务需要分配不同大小的线程池
1、CPU密集型
尽量使用较小的线程池，一般Cpu核心数+1
因为CPU密集型任务CPU的使用率很高，若开过多的线程，只能增加线程上下文的切换次数，带来额外 的开销
2、IO密集型 方法一:可以使用较大的线程池，一般CPU核心数 * 2
 
IO密集型CPU使用率不高，可以让CPU等待IO的时候处理别的任务，充分利用cpu时间 方法二:线程等待时间所占比例越高，需要越多线程。线程CPU时间所占比例越高，需要越少线程。
下面举个例子: 比如平均每个线程CPU运行时间为0.5s，而线程等待时间(非CPU运行时间，比如IO)为1.5s，CPU核 心数为8，那么根据上面这个公式估算得到:((0.5+1.5)/0.5)*8=32。这个公式进一步转化为:
最佳线程数目 = (线程等待时间与线程CPU时间之比 + 1)* CPU数目
3、混合型 可以将任务分为CPU密集型和IO密集型，然后分别使用不同的线程池去处理，按情况而定
```

## Java多线程：向线程传递参数的三种方法
```text
https://www.cnblogs.com/jpfss/p/10783847.html

一、通过构造方法传递数据 
二、通过变量和方法传递数据
三、通过回调函数传递数据
```

## 线程的几种状态
```text
线程在一定条件下，状态会发生变化。线程一共有以下几种状态:
1. 新建状态(New):新创建了一个线程对象。
2. 就绪状态(Runnable):线程对象创建后，其他线程调用了该对象的start()方法。该状态的线程位于 “可运行线程池”中，变得可运行，只等待获取CPU的使用权。即在就绪状态的进程除CPU之外，其 它的运行所需资源都已全部获得。
3. 运行状态(Running):就绪状态的线程获取了CPU，执行程序代码。
4. 阻塞状态(Blocked):阻塞状态是线程因为某种原因放弃CPU使用权，暂时停止运行。直到线程进入就绪状态，才有机会转到运行状态。
  阻塞的情况分三种:
    1. 等待阻塞:运行的线程执行wait()方法，该线程会释放占用的所有资源，JVM会把该线程放入 “等待池”中。进入这个状态后，是不能自动唤醒的，必须依靠其他线程调用notify()或 notifyAll()方法才能被唤醒，
    2. 同步阻塞:运行的线程在获取对象的同步锁时，若该同步锁被别的线程占用，则JVM会把该线 程放入“锁池”中。
    3. 其他阻塞:运行的线程执行sleep()或join()方法，或者发出了I/O请求时，JVM会把该线程置为 阻塞状态。当sleep()状态超时、join()等待线程终止或者超时、或者I/O处理完毕时，线程重新 转入就绪状态。
5. 死亡状态(Dead):线程执行完了或者因异常退出了run()方法，该线程结束生命周期。

```
