/**
 * 
 */
package sc.provider;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author jiang
 *
 */
@RestController
public class ProviderController {
    private final Logger logger = LoggerFactory.getLogger(ProviderController.class);

    String APP_UUID = "";
    
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(@RequestParam String str) {
    	logger.info(str);
        return str;
    }
    
    @RequestMapping(value = "/getUUID", method = RequestMethod.GET)
    public String getUUID() {
    	if(StringUtils.isEmpty(APP_UUID)){
    		APP_UUID = UUID.randomUUID().toString().replaceAll("-", "");
    	}
    	return APP_UUID;
    }
}

