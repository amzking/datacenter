# datacenter
version-dev-2.0 


# 新版本的datacenter

## 本次修改主要有以下几个方面：
  * 基于netty，重新实现Http Server。
  * 去Spring， Spring全家桶过于庞大，启动较多的非必须项。
  * 数据库基于MongoDB，实现读写分离。
  * 拆分服务，API部分从原来体系中独立，由golang实现，单独访问读库。
  * docker容器化（后期）
  
  
  ### Netty Http Server
  * 一个独立的HttpServer，可独立部署，可集成SpringMVC框架
  * 处理GET，POST，DELETE，OPTION
  * 可以处理媒体类型，提供对不同类型解析接口。
  * 异常管理
