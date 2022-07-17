# awesome-utils

短小精悍的Java工具类

## 为什么有这个项目

在有这么多成熟工具类的时代，为什么还要写一个工具类仓库？

- 某些项目可能不允许引入第三方工具类
- 大型开源工具类使用过于非人性化
- 总有自定义工具类的需求
- 图方便（懒）

## 如何使用这个项目

把大象放进冰箱只需要三步：

1. 通过下面的文档，找到需要的工具类方法。
2. 复制黏贴进你的项目，引入可能需要的依赖。
3. 调用它！

## 项目文档

以下为项目中各个maven仓库的介绍，每个maven仓库完全独立，**并非父子项目。**

### common-utils 通用工具类仓库

- utils/：工具类
  - DateTimeUtil：日期时间相关
  - IPAddressUtil：IP地址处理相关
  - MapUtil：JavaMap相关
  - PageUtil：通用分页相关

- algorithm/：算法
  - lru/：最近最久未使用算法
    - LRUWithHashMap: HashMap实现LRU
    - LRUWithLinkedHashMap: LinkedHashMap实现LRU

- io/：JavaIO
  - socket/：Socket编程
    - TCP: TCP服务端和客户端实现
    - UDP: UDP服务端和客户端实现
  - nio/：NIO编程

- framework/：常用框架
  - colaExtension/：cola扩展点框架
  - springStateMachine/：spring状态机框架
  - eventBus/：guava EventBus框架

- thread/：Java线程
  - forkjoin/：forkjoin相关
  - reactor/：reactor框架
  - threadPool/：线程池相关

## 如何提交你的优秀工具类

1. 加入新的方法或者类，完善好Java Doc！
2. 为自己的方法撰写单元测试，如果你懒，也可以不写！
3. 提交pull request！