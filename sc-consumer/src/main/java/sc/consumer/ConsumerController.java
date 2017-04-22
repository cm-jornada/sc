/**
 * 
 */
package sc.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiang
 *
 */
@RestController
public class ConsumerController {

    @Autowired
    ConsumerClient computeClient;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(String str) {
    	String provider = IPUtil.getIP();
        return provider + "::::::" + computeClient.test(str);
    }

}
