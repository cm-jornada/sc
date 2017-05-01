/**
 * 
 */
package sc.consumer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


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
    private RestTemplate restTemplate;
    
    Map<String, Integer> index = new ConcurrentHashMap<>();

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(String str) {
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
    	
    	ResponseEntity<String> response = restTemplate.exchange("http://sc-provider/getUUID",
   			 HttpMethod.GET, null, String.class);
    	System.out.println(response.getBody());
    	
    	String UUID = response.getBody();
		if(null == index.get(UUID)){
			index.put(UUID, 0);
		}else{
			index.put(UUID, index.get(UUID) + 1);
		}
    }

}
