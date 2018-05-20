package utils;

import cucumber.api.DataTable;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import pojo.RestResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inputs {

    public static RestResponse getRestResponseFromDataTable(DataTable restResponse) {
        RestResponse response = null;
        for (Map<String, String> data : restResponse.asMaps(String.class, String.class)) {
            String messages = data.get("messages");
            int responseCode = Integer.valueOf(data.get("responseCode"));
            String name = data.get("name");
            String alpha2_code = data.get("alpha2_code");
            String alpha3_code = data.get("alpha3_code");
            response = new RestResponse(messages, responseCode, name, alpha2_code, alpha3_code);
        }
        return response;
    }

    public static RestResponse getRestResponseFromResponse(Response response) {

        int responseCode = response.getStatusCode();

        JsonPath jsonPath = response.jsonPath();
        Map<String, Object> map = jsonPath.getMap("RestResponse");

        String message = ((List<String>) map.get("messages")).get(0);
        HashMap<String, String> result = (HashMap<String, String>) map.get("result");
        String name = result.get("name");
        String alpha2_code = result.get("alpha2_code");
        String alpha3_code = result.get("alpha3_code");

        RestResponse restResponse = new RestResponse(message, responseCode, name, alpha2_code, alpha3_code);

        return restResponse;
    }

    public static String getJsonFromPair(DataTable body) {
        String jsonString = null;
        String key = null;
        String value = null;
        for (Map<String, String> data : body.asMaps(String.class, String.class)) {
            key = data.get("key");
            value = data.get("value");
            jsonString = twoStringToJson(key, value);
        }
        return jsonString;
    }

    public static String twoStringToJson(String key, String value) {
        return String.format("`%s`:`%s`", key, value).replace('`', '"');
    }

}
