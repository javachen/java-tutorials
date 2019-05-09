# spring-cloud-consul

## 概念

- Consul 组件
 - 服务发现
 - 健康检查
 - 键值存储
 - 多数据中⼼
 
理理解 Raft 协议: http://thesecretlivesofdata.com/raft/

## 安装consul

安装：

brew install consul
  
后台启动：

brew services start consul  

前台启动：

consul agent -dev -advertise 127.0.0.1