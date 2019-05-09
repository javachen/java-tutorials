# spring-cloud-eureka-server

## Config 

1、Maven

~~~xml
    <properties>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
        <java.version>1.8</java.version>
        <spring-cloud-dependencies.version>Greenwich.RELEASE</spring-cloud-dependencies.version>
    </properties>

    <dependencies>
         <dependency>
             <groupId>org.springframework.cloud</groupId>
             <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
         </dependency>
     </dependencies>
     
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-starter-parent</artifactId>
                <version>${spring-cloud-dependencies.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
~~~

2、Main Class

~~~java
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
~~~

3、application.yml

~~~
server:
  port: 8761

spring:
  application:
    name: spring-cloud-eureka-server

eureka:
  instance:
    hostname: eureka-8761.com
  client:
    registerWithEureka: false
    fetchRegistry: false
    serviceUrl:
      defaultZone: http://eureka-8761.com:8761/eureka/
~~~

4、http://eureka-8761.com:8761/

## HA

1、application.yml

~~~yml
server:
  port: 8761

spring:
  application:
    name: spring-cloud-eureka-server
  profiles:
    active: eureka-server

eureka:
  instance:
    hostname: localhost
  client:
    registerWithEureka: false
    fetchRegistry: false

---
server:
  port: 8761

spring:
  application:
    name: spring-cloud-eureka-server

eureka:
  instance:
    hostname: eureka-8761.com
  client:
    registerWithEureka: false
    fetchRegistry: false
    serviceUrl:
      defaultZone: http://eureka-8761.com:8761/eureka/

---
spring:
  profiles: eureka-server1

server:
  port: 8761

eureka:
  instance:
    hostname: eureka-8761.com
  client:
    registerWithEureka: false
    fetchRegistry: false
    serviceUrl:
        defaultZone: http://eureka-8762.com:8762/eureka/,http://eureka-8763.com:8763/eureka/

---
spring:
  profiles: eureka-server2

server:
  port: 8762

eureka:
  instance:
    hostname: eureka-8762.com
  client:
    registerWithEureka: false
    fetchRegistry: false
    serviceUrl:
      defaultZone: http://eureka-8761.com:8761/eureka/,http://eureka-8763.com:8763/eureka/

---
spring:
  profiles: eureka-server3

server:
  port: 8763

eureka:
  instance:
    hostname: eureka-8763.com
  client:
    registerWithEureka: false
    fetchRegistry: false
    serviceUrl:
      defaultZone: http://eureka-8761.com:8761/eureka/,http://eureka-8762.com:8762/eureka/
~~~

2、run server

run EurekaServerApplication with  `--spring.profiles.active=eureka-server1`
run EurekaServerApplication with  `--spring.profiles.active=eureka-server2`
run EurekaServerApplication with  `--spring.profiles.active=eureka-server3`