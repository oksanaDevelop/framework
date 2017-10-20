package utils;


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


    public static Response getGetResponse(String endpointURL){
        RequestSpecification httpRequest = RestAssured.given();
        return httpRequest.request(Method.GET, endpointURL);
    }

    private static Response getPostResponse(String endpointURL, String bodyJson){
        RequestSpecification httpRequest = RestAssured.given().body(bodyJson);
        return httpRequest.request(Method.POST, endpointURL);
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

        Response response = getPostResponse();
        System.out.println("******");
        System.out.println(response.print());
    }


}
