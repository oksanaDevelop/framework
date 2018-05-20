package utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class GlobalVariables {

    private static ThreadLocal<Map<String, Object>> globalVariables =new ThreadLocal<>();
    private final static Logger log = LogManager.getLogger(GlobalVariables.class);


    static{
        Map<String, Object> map = new HashMap();
        globalVariables.set(map);
    }

    public static WebDriver getWebDriver(){
        WebDriver res = null;

        for (Map.Entry<String, Object> map : globalVariables.get().entrySet()) {
            if((map.getKey().equals("WebDriver"))) res = (WebDriver) map.getValue();
        }
        log.info("Take a WebDriver from globalVariables");
        return res;
    }

    public static void setWebdriver(WebDriver driver){
        globalVariables.get().put("WebDriver", driver);
        log.info("Put Webdriver to globalVariables.");
        if (driver==null)
            log.info("Webdriver is NULL");
    }

    public static void setStringVariable(String key, String value){
        globalVariables.get().put(key, value);
    }

    public static String getStringVariable(String key){
            return (String) globalVariables.get().get(key);
    }

    public static void setObjectVariable(String key, Object value){
        globalVariables.get().put(key, value);
    }

    public static Object getObjectVariable(String key){
        return  globalVariables.get().get(key);
    }

    public static void removeStringVariable(String key){
        if (globalVariables.get().containsKey(key)) globalVariables.get().remove(key);
    }


}
