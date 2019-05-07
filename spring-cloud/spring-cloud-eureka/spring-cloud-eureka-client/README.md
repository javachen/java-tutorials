# spring-cloud-eureka-client

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

2、Main Class

~~~java
@SpringBootApplication
public class EurekaClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }
}
~~~

3、application.yml

~~~
spring:
  application:
    name: spring-cloud-eureka-client

server:
  port: 8701

eureka:
  client:
    registerWithEureka: true
    fetchRegistry: true
    serviceUrl:
      defaultZone: ${EUREKA_URI:http://localhost:8761/eureka/}
  instance:
    preferIpAddress: true
~~~

4、http://localhost:8701/greeting