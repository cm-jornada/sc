/**
 * 
 */
package sc.consumer;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author jiang
 *
 */
@FeignClient(value = "consumerClient")
public interface ConsumerClient {

    @RequestMapping(method = RequestMethod.GET, value = "/test")
    String test(@RequestParam("str") String str);
    
}