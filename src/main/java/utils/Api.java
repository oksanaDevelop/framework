package utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import pojo.RestResponse;
import pojo.Result;
import sun.rmi.runtime.Log;

import java.io.IOException;

import java.util.Map;

@Component
public class Api {

    private final static Logger log = Logger.getLogger(Api.class);


    public static Response getGetResponse(String endpointURL){
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, endpointURL);
        log.info(response.print());
        return response;
    }

    public static Response getPostResponse(String endpointURL, String bodyJson){
        RequestSpecification httpRequest = RestAssured.given().body(bodyJson);
        Response response = httpRequest.request(Method.POST, endpointURL);
        log.info(String.format("%n ***** %n POST response to endpoint %s with JSON in body %s is %n %s", endpointURL, bodyJson, response.asString()).replace('`','"'));
        return response;
    }

    private static Response getGetResponseWResource(String baseURL, String resourceName){
        String endpointURL = baseURL+"/"+resourceName;
        return getGetResponse(endpointURL);
    }

    private static Response getPostResponse(){
        String endpointURL = "https://httpbin.org/post";
        String Json = getResultJson("USA");
             return getPostResponse(endpointURL, Json);
    }


    private static Response getResponseCountries( String resourceName){
        String baseURI = "http://services.groupkt.com/country";
        return getGetResponseWResource(baseURI, resourceName);
    }

    private static Response getCountryByIso3Code(String iso3Code){
        String resourceName = "get/iso3code/"+iso3Code;
        return getResponseCountries(resourceName);
    }

    public static Response searchCountry(String country){
        String resourceName = "search?text="+country;
        return getResponseCountries(resourceName);
    }

    public static String getResultJson(String countryCode3){
        Response response = getCountryByIso3Code(countryCode3);
        System.out.println(response.asString());
        JsonPath jsonPath = response.jsonPath();
        Map<String, String> map = jsonPath.getMap("RestResponse.result");
        String res = null;
        try {
             res = new ObjectMapper().writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return res;
    }

    public static String getRestResponseJsonAndCode(String countryCode3){
        Response response = getCountryByIso3Code(countryCode3);
        System.out.println(response.asString());
        JsonPath jsonPath = response.jsonPath();
        Map<String, String> map = jsonPath.getMap("RestResponse");
        map.put("responseCode", String.valueOf(response.getStatusCode()));
        String res = null;
        try {
            res = new ObjectMapper().writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return res;
    }


    public static Result getResultObject(String countryCode3){
        String json = getResultJson(countryCode3);
        System.out.println(json);
        Result result = null;

        ObjectMapper mapper = new ObjectMapper();
        try {
            result = mapper.readValue(json, Result.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static RestResponse getRestResponseObject(String countryCode3){
        String json = getRestResponseJsonAndCode(countryCode3);
        System.out.println(json);
        RestResponse result = null;

        ObjectMapper mapper = new ObjectMapper();
        try {
            result = mapper.readValue(json, RestResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static String getCountry(String countryCode3){
        Response response = getCountryByIso3Code(countryCode3);
        System.out.println(response.getBody().asString());
        JsonPath jsonPath = response.jsonPath();
        Map<String, String> map = jsonPath.getMap("RestResponse.result");
        return map.get("name");
    }

//    public static String getJsonStringFromCountry(){
//        Result country = new Result("United States of America", "USA", "US");
//        String res = null;
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//             res = mapper.writeValueAsString(country);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//return res;
//    }

    public static void main(String[] args) {

//        Result result = new Result("United States of America", "US", "USA" );
//        RestResponse restResponseOur = new RestResponse("Country found matching code [USA].", result, 200);
//        RestResponse restResponse = getRestResponseObject("USA");
//       // Result result = getResultObject("USA");
//        System.out.println(restResponseOur);
//        System.out.println(restResponse);
//        System.out.println(restResponseOur.compareTo(restResponse));

       // Response response = getPostResponse();
        Response response = getPostResponse("https://httpbin.org/post", "{`mybody`:`body`}".replace('`', '"'));
       // Response response = getPostResponse("https://httpbin.org/post", "{\"name\":\"United States of America\",\"alpha3_code\":\"USA\",\"alpha2_code\":\"US\"}");
        System.out.println("******");
        System.out.println(response.body().asString());
    }


}
