package utils;


import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class GlobalVariables {

   // private static ThreadLocal<Map<String, Object>> globalVeriables = new ThreadLocal<>().set(new HashMap<String, Object>());
    private static ThreadLocal<Map<String, Object>> globalVeriables =new ThreadLocal<>();
    private final static Logger log = Logger.getLogger(GlobalVariables.class);


    static{
        Map<String, Object> map = new HashMap();
        globalVeriables.set(map);
    }

    public static WebDriver getWebDriver(){
        WebDriver res = null;

        for (Map.Entry<String, Object> map : globalVeriables.get().entrySet()) {
            if((map.getKey().equals("WebDriver"))) res = (WebDriver) map.getValue();
        }
        return res;
    }

    public static void setWebdriver(WebDriver driver){
        globalVeriables.get().put("WebDriver", driver);
        log.info("Put Webdriver to globalVariables.");
        if (driver==null)
            log.info("Webdriver is NULL");
    }

    public static void setStringVariable(String key, String value){
        globalVeriables.get().put(key, value);
    }

    public static String getStringVariable(String key){
            return (String)globalVeriables.get().get(key);
    }

    public static void removeStringVariable(String key){
        if (globalVeriables.get().containsKey(key)) globalVeriables.get().remove(key);
    }


}
