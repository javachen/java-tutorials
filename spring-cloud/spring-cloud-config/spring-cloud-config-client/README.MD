# spring-cloud-config-client

### 项目设置和依赖

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-config</artifactId>
    </dependency>
</dependencies>
```

### 创建启动类

```java
@SpringBootApplication
public class ConfigClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
    }
}
```

### 创建application.yml

```yml
spring:
  application:
    name: spring-cloud-config-client

server:
  port: 8201

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
```

### 创建bootstrap.yml

```yml
spring:
  cloud:
    #spring.cloud.config.name 是可选的，如果没有配置，采用 ${spring.application.name}
    config:
      name: spring-cloud-config-client #需要从github上读取的资源名称，注意没有yml后缀名
      label: master
      uri: http://localhost:3344  #本微服务启动后先去找3344号服务，通过SpringCloudConfig获取GitHub的服务地址
```

### 动态配置属性Bean

```java
@RefreshScope
@Configuration
@ConfigurationProperties("my")
public class MyProperties {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

### 创建controller

```java
@RestController
public class DistributedPropertiesController {

    @Value("${my.name}")
    String value;

    @Autowired
    private MyProperties properties;

    @GetMapping("/getConfigFromValue")
    public String getConfigFromValue() {
        return value;
    }

    @GetMapping("/getConfigFromProperty")
    public String getConfigFromProperty() {
        return properties.getName();
    }

}
```

### 查看服务端配置文件内容

http://localhost:3344/spring-cloud-config-client.yml

### 客户端访问查看结果

http://localhost:8201/getConfigFromValue

```json
{
    "name": "aaa"
}
```

http://localhost:8201/getConfigFromProperty

```json
{
    "name": "aaa"
}
```

### 刷新配置

#### 调用refresh端口

手动修改git仓库配置文件中name的值为 bbb ，然后使用postman通过post方法执行 http://localhost:8201/actuator/refresh 

再次访问 http://localhost:8201/user

```json
{
    "name": "aaa"
}
```

http://localhost:8201/getConfigFromProperty

```json
{
    "name": "bbb"
}
```

#### 编写代码设置定时器刷新配置

```java
@SpringBootApplication
@EnableScheduling
public class ConfigClientApplication {
    private final ContextRefresher contextRefresher;

    @Autowired
    public ConfigClientApplication(ContextRefresher contextRefresher) {
        this.contextRefresher = contextRefresher;
    }

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
    }

    @Scheduled(fixedRate = 3000L)
    public void update() {
        Set<String> keys = contextRefresher.refresh();

        if (!keys.isEmpty()) {
            System.out.println("本次更新的配置项: " + keys);
        }
    }
}
```

手动修改git仓库配置文件中name的值为 ccc 。

#### 设置Git仓库webhook自动触发更新配置

修改 https://github.com/javachen/spring-cloud-config/settings/hooks/new 

> 注意：这里使用的是 localhost 不是外网地址，可能访问不成功。

### 扩展 health 

```java
@Bean
public MyHealthIndicator myHealthIndicator() {
    return new MyHealthIndicator();
}

private class MyHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        Health.Builder builder = Health.status(Status.UP);
        builder.withDetail("name", "MyHealthIndicator");
        builder.withDetail("timestamp", System.currentTimeMillis());
        return builder.build();
    }
}
```

## Spring Cloud Config 客户端集成 Eureka 

### 引入 Maven 依赖

```xml
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

## 配置 Config 客户端配置

bootstrap.properties

```yml
spring:
  cloud:
    config:
      name: spring-cloud-config-client #需要从github上读取的资源名称，注意没有yml后缀名
      label: master
      ## 激活 Config 服务器发现
      discovery:
        enabled: true
        ## 配置 Config 服务器的应用名称（Service ID）
        serviceId: spring-cloud-config-server
        
eureka:
  client:
    serviceUrl:
      defaultZone: http://eureka-8761.com:8761/eureka/        
```

> 注意配置文件的变动

### 客户端访问查看结果
    
http://localhost:8201/getConfigFromProperty
