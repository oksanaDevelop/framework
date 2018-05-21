package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.stereotype.Component;
import pojo.RestResponse;
import pojo.Result;
import java.io.IOException;
import java.util.Map;

@Component
public class Api {

    private final static Logger log = LogManager.getLogger(Api.class);

    public static Response getGetResponse(String endpointURL) {
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, endpointURL);
        log.info(String.format("%n ***** %n GET response to endpoint %s is %n %s", endpointURL, response.asString()));

        log.info(response.print());
        return response;
    }

    public static Response getPostResponse(String endpointURL, String bodyJson) {
        RequestSpecification httpRequest = RestAssured.given().body(bodyJson);
        Response response = httpRequest.request(Method.POST, endpointURL);
        log.info(String.format("%n ***** %n POST response to endpoint %s with JSON in body %s is %n %s", endpointURL, bodyJson, response.asString()));
        return response;
    }

    private static Response getGetResponseWResource(String baseURL, String resourceName) {
        String endpointURL = baseURL + "/" + resourceName;
        return getGetResponse(endpointURL);
    }

    private static Response getPostResponse() {
        String endpointURL = "http://httpbin.org/post";
        String Json = getResultJson("USA");
        return getPostResponse(endpointURL, Json);
    }

    private static Response getResponseCountries(String resourceName) {
        String baseURI = "http://services.groupkt.com/country";
        return getGetResponseWResource(baseURI, resourceName);
    }

    private static Response getCountryByIso3Code(String iso3Code) {
        String resourceName = "get/iso3code/" + iso3Code;
        return getResponseCountries(resourceName);
    }

    public static Response searchCountry(String country) {
        String resourceName = "search?text=" + country;
        return getResponseCountries(resourceName);
    }

    private static String getResultJson(String countryCode3) {
        Response response = getCountryByIso3Code(countryCode3);
        JsonPath jsonPath = response.jsonPath();
        Map<String, String> map = jsonPath.getMap("RestResponse.result");
        return getStringFromMap(map);
    }

    private static String getRestResponseJsonAndCode(String countryCode3) {
        Response response = getCountryByIso3Code(countryCode3);
        JsonPath jsonPath = response.jsonPath();
        Map<String, String> map = jsonPath.getMap("RestResponse");
        map.put("responseCode", String.valueOf(response.getStatusCode()));
        return getStringFromMap(map);
    }

    public static Result getResultObject(String countryCode3) {
        String json = getResultJson(countryCode3);
        Result result = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            result = mapper.readValue(json, Result.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static RestResponse getRestResponseObject(String countryCode3) {
        String json = getRestResponseJsonAndCode(countryCode3);
        log.info(String.format("JSON response is - %s",json ));
        RestResponse result = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            result = mapper.readValue(json, RestResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getCountry(String countryCode3) {
        Response response = getCountryByIso3Code(countryCode3);
        log.info(String.format("getCountry method, body is - %s"), response.getBody().asString());
        JsonPath jsonPath = response.jsonPath();
        Map<String, String> map = jsonPath.getMap("RestResponse.result");
        return map.get("name");
    }

    private static String getStringFromMap(Map<String, String> map){
        String res = null;
        try {
            res = new ObjectMapper().writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return res;
    }
}
