# spring-cloud-zuul-server

## Config 

1、Eureka Server

2、Maven

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
            <artifactId>spring-cloud-starter-netflix-zuul</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
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

3、Main Class

~~~java
@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class ZuulServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulServerApplication.class, args);
    }
}

~~~

4、application.yml

~~~
spring:
  application:
    name: spring-cloud-zuul-server

server:
  port: 8700

eureka:
  client:
    registerWithEureka: true
    fetchRegistry: true
    serviceUrl:
      defaultZone: ${EUREKA_URI:http://localhost:8761/eureka/}
  instance:
    preferIpAddress: true
~~~

5、http://localhost:8700/routes