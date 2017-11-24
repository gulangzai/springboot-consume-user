package com.jiujichina;
  
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.jiujichina.model.TbUser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@EnableCircuitBreaker
@RestController
public class MovieController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	
    @HystrixCommand(fallbackMethod = "findByIdFallback",commandProperties={
    		@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="5000"),
    		@HystrixProperty(name="metrics.rollingStats.timeInMilliseconds",value="10000")
    },threadPoolProperties={
    		@HystrixProperty(name="coreSize",value="1"),
    		@HystrixProperty(name="maxQueueSize",value="10")
    })
    @GetMapping("/user/{id}") 
	public TbUser findById(@PathVariable String id){
    	 return this.restTemplate.getForObject("http://springboot-provider-user-service/"+id, TbUser.class);
	}
    
    public TbUser findByIdFallback(String id) {
    	TbUser user = new TbUser();
    	user.setId("0");
    	user.setAge(22);
    	user.setName("默认用户");
    	return user;
    }
    
	
    @RequestMapping("/log-instance")
	public void logUserInstance(){
		ServiceInstance serviceInstance = this.loadBalancerClient.choose("springboot-provider-user-service");
		MovieController.LOGGER.info(serviceInstance.getServiceId()+" "+serviceInstance.getHost()+" "+serviceInstance.getPort());
	} 
		
}
