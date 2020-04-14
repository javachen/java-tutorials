# How to Use Ribbon Without Eureka


## 概念

### 实际请求客户端

* LoadBalancerClient
  * RibbonLoadBalancerClient

### 负载均衡上下文

* LoadBalancerContext
  * RibbonLoadBalancerContext

### 负载均衡器

* ILoadBalancer
  * BaseLoadBalancer
  * DynamicServerListLoadBalancer
  * ZoneAwareLoadBalancer
  * NoOpLoadBalancer

### 负载均衡规则

核心规则接口

* IRule
  * 随机规则：RandomRule
  * 最可用规则：BestAvailableRule
  * 轮训规则：RoundRobinRule
  * 重试实现：RetryRule
  * 客户端配置：ClientConfigEnabledRoundRobinRule
  * 可用性过滤规则：AvailabilityFilteringRule
  * RT权重规则：WeightedResponseTimeRule
  * 规避区域规则：ZoneAvoidanceRule

PING 策略接口

* IPingStrategy

PING 接口

* IPing
  * NoOpPing
  * DummyPing
  * PingConstant
  * PingUrl

Discovery Client 实现

* NIWSDiscoveryPing

### 选择服务器逻辑
    
LoadBalancerClient（LoadBalancerClient） -> ILoadBalancer（ZoneAwareLoadBalancer） ->  IRule (ZoneAvoidanceRule)


## 配置 Ribbon 客户端

1、引入 Maven 依赖

~~~xml
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
    </dependency>
~~~

2、激活 Ribbon 客户端

~~~java
@SpringBootApplication
@RibbonClient(name = "producer", configuration = RibbonConfig.class)
public class ConsumerRibbonApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerRibbonApplication.class, args);
    }
}
~~~


3、application.yml

~~~yml
spring:
  application:
    name: consumer-ribbon

server:
  port: 9096

# 暂时性关闭 Eureka 注册
ribbon:
  eureka:
    enabled: false

producer:
  ribbon:
    listOfServers: localhost:8081
~~~

4、Service

~~~java
@Service
@Slf4j
public class GreetingService {
    private String REST_URL_PREFIX = "producer";

    @Autowired
    private RestTemplate restTemplate;

    public String greeting(String username) {
        return restTemplate.getForObject("http://"+REST_URL_PREFIX + "/greeting/{username}", String.class, username);
    }
}
~~~

5、调整 RestTemplate

~~~java
@Configuration
public class ConfigBean {
    @Bean
    @LoadBalanced //给RestTemplate添加了拦截器，修改了代码
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
~~~

6、http://localhost:9096/greeting/aaaa