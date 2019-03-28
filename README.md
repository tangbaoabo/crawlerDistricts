# crawlerDistricts
# 用高德提供的API获取所有省市地区：
## 开发环境：jdk11.0.2
## 开发框架：SpringBoot 2.1.3
## 数据库：MySQL
项目结构：
```plain

+--------+ CralwerCity
|
|
+---+boot
|
+---+core
|
+---+worker
|
+----+saver
|
+----+scheduler
|
+----+service
|
|
+-----> springbootApp

```
程序所有的实例交由spring管理

boot是程序的初始化和启动类，里面可以配置保存类，worker，scheduler等等，当然还可以配置你自己的实现类

core是程序的核心，里面包含了算法还有怎么调度等

worker是封装了一些繁琐而又简单的操作，比如解析json，校验等等

saver是一个终端操作，可以将处理好的数据持久化

提醒：
  1：本程序所用到的开发者key是本人自己的，在application.yml中配置调用量只有2k，有需要的用户请到https://lbs.amap.com/dev/key/app申请，很简单
  2：因为数据量比较小，本程序暂时更新了单线程版本的，不适用多线程环境下使用，后续会更新多线程版本
