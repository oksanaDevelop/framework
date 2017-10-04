package utils;


import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class GlobalVariables {

   // private static ThreadLocal<Map<String, Object>> globalVeriables = new ThreadLocal<>().set(new HashMap<String, Object>());
    private static ThreadLocal<Map<String, Object>> globalVeriables;

    static{
        Map<String, Object> map = new HashMap();
        globalVeriables = new ThreadLocal<>();
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
    }

}
