/**
 * 
 */
package sc.consumer;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author jiang
 *
 */
@RestController
public class ConsumerController {

	private final Logger logger = LoggerFactory.getLogger(ConsumerController.class);
	
    @Autowired
    ConsumerClient consumerClient;
    
    @Autowired
    private DiscoveryClient client;
    
    Map<String, Integer> index = new ConcurrentHashMap<>();

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(String str) {
    	List<String> servicsList = client.getServices();
    	for (String string : servicsList) {
    		List<ServiceInstance> inList = client.getInstances(string);
    		for (ServiceInstance serviceInstance : inList) {
    			System.out.println("实例:"+serviceInstance);;
			}
		}
        return str + "::::::" + consumerClient.test(str);
    }
    
    @RequestMapping(value = "/getUUID", method = RequestMethod.GET)
    public String getUUID(String timerFlag) {
    	if("true".equals(timerFlag)){
    		for (int i = 0; i < 100; i++) {
    			doservice();
			}
    	}else{
    		try {
    			doservice();
			} catch (Exception e) {
				logger.error("service error", e);
				e.printStackTrace();
			}
    	}
    	return index.toString();
    }
    
    private void doservice(){
    	String UUID = consumerClient.getUUID();
		if(null == index.get(UUID)){
			index.put(UUID, 0);
		}else{
			index.put(UUID, index.get(UUID) + 1);
		}
    }

}
