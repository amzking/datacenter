# datacenter
version-dev-2.0 


# 新版本的datacenter

## 本次修改主要有以下几个方面：
  * 基于netty，重新实现Http Server
  * 实现对Tomcat对兼容
  * 去Spring， Spring全家桶过于庞大，启动较多的非必须项
  * 数据库基于MongoDB，实现读写分离。
  * 拆分服务，API部分从原来体系中独立，由golang实现，单独访问读库。
  * docker容器化（后期）
