# spring-cloud-config-server

## 搭建 Spring Cloud Config 服务器

### 项目设置和依赖

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-config-server</artifactId>
    </dependency>
</dependencies>
```

### 创建启动类

```java
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
```

### 创建配置仓库

#### 基于本地文件

1、在项目的根目录下，创建一个名为“configs”，它的绝对路径：${user.dir}/config

2、修改配置文件：

```yml
server:
  port: 3344

spring:
  application:
    name: spring-cloud-config-server
  cloud:
    config:
      server:
        git:
          uri: ${user.dir}/configs
```

3、在configs目录创建配置文件：spring-cloud-config-client.yml

```yml
test:
  id: 12
  name: aaa
```

4、初始化本地 git 仓库

```bash
git init
git add .
git commit -m "First commit"
```

#### 基于Git远程仓库

1、配置远程 Git 仓库地址

```yml
server:
  port: 3344


spring:
  application:
    name: spring-cloud-config-server
  cloud:
    config:
      server:
        git:
          # 强制拉取 Git 内容
          force-pull: true
          #GitHub上面的git仓库名字
          uri: git@github.com:javachen/spring-cloud-config.git
```          

2、本地 clone 远程Git 仓库

git clone git@github.com:javachen/spring-cloud-config.git

3、创建配置文件：spring-cloud-config-client.yml
  
```yml
test:
  id: 12
  name: aaa
```
  
4、提交到远程 Git 仓库 

```bash
git add spring-cloud-config-client.yml
git commit -m "add spring-cloud-config-client.yml"
git push 
```

### 启动应用

运行 ConfigServerApplication 类

### 查询配置

可以在客户端通过以下几种方式访问配置文件：

```
/{application}/{profile}[/{label}]
/{application}-{profile}.yml
/{label}/{application}-{profile}.yml
/{application}-{profile}.properties
/{label}/{application}-{profile}.properties
```

## Spring Cloud Config 服务端集成 Eureka  

### 引入 Maven 依赖

```xml
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

### 激活 Eureka 客户端

```java
@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class ConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
```

### 调整 spring-cloud-config-server 配置

spring-cloud-config-server 注册到 Eureka 服务器

```yml
server:
  port: 3344

management:
  endpoints:
    web:
      exposure:
        # 开放所有监控端点
        include: '*'
  endpoint:
    health:
      # 是否展示健康检查详情
      show-details: always
      
eureka:
  client:
    serviceUrl:
      defaultZone: http://eureka-8761.com:8761/eureka/
      
spring:
  application:
    name: spring-cloud-config-server
  cloud:
    config:
      server:
        git:
#          uri: ${user.dir}/configs
          #GitHub上面的git仓库名字
          uri: git@github.com:javachen/spring-cloud-config.git
```