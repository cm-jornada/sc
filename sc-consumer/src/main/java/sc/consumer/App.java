package sc.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


/**
 * Hello world!
 *
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class App 
{
    
  @Autowired  
  private RestTemplateBuilder builder;  
  
  @Bean  
  @LoadBalanced  // 添加负载均衡支持，很简单，只需要在RestTemplate上添加@LoadBalanced注解，那么RestTemplate即具有负载均衡的功能  
  public RestTemplate restTemplate() {  
      return builder.build();  
  }  
  
    public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args);
    }
}
