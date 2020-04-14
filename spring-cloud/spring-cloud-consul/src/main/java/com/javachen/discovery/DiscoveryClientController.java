package com.javachen.discovery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.naming.ServiceUnavailableException;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RestController
public class DiscoveryClientController {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String currentApplicationName;
    private DiscoveryClient discoveryClient;

    @Autowired
    public DiscoveryClientController(DiscoveryClient discoveryClient,
                                     @Value("${spring.application.name}") String currentApplicationName) {
        this.discoveryClient = discoveryClient;
        this.currentApplicationName = currentApplicationName;
    }

    @GetMapping("/discoveryClient")
    public String discoveryPing() throws RestClientException, ServiceUnavailableException {
        URI service = serviceUrl().map(s -> s.resolve("/ping"))
                .orElseThrow(ServiceUnavailableException::new);
        return restTemplate.getForEntity(service, String.class)
                .getBody();
    }

    /**
     * 获取当前应用信息
     *
     * @return
     */
    @GetMapping("/current-instance")
    public ServiceInstance getCurrentServiceInstance() {
        return discoveryClient.getInstances(currentApplicationName).get(0);

    }

    @GetMapping("/list/services")
    public List<String> listServices() {
        return discoveryClient.getServices();
    }

    /**
     * 获取所有的服务实例信息
     *
     * @return
     */
    @GetMapping("/list/service-instances")
    public List<ServiceInstance> listServiceInstances() {
        List<String> services = listServices();
        List<ServiceInstance> serviceInstances = new LinkedList<>();

        services.forEach(serviceName -> {
            serviceInstances.addAll(discoveryClient.getInstances(serviceName));
        });

        return serviceInstances;
    }

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @GetMapping("/my-health-check")
    public ResponseEntity<String> myCustomCheck() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public Optional<URI> serviceUrl() {
        return discoveryClient.getInstances(currentApplicationName)
                .stream()
                .findFirst()
                .map(si -> si.getUri());
    }

}
