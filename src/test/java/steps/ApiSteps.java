package steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import pojo.RestResponse;
import utils.Api;
import utils.GlobalVariables;

import java.util.List;

public class ApiSteps {

    @Autowired
    Api api;

    @When("^User sends GET request to endpoint (.*)$")
    public void user_sends_Get_request_to_endpoint_http_services_groupkt_com_country(String endpoint)  {
        Response response = api.getGetResponse(endpoint);
        GlobalVariables.setObjectVariable("response", response);
    }

    @Then("^User receive response with the following data$")
   // public void user_receive_response_with_the_following_data(DataTable expectedRestResponse) {
    public void user_receive_response_with_the_following_data(List<RestResponse> usercredentials) {
        System.out.println("*****************");
        System.out.println(usercredentials.get(0));


    }
}
