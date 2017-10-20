package steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import pojo.RestResponse;
import utils.Api;
import utils.GlobalVariables;
import utils.Inputs;

import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;

public class ApiSteps {

    @Autowired
    Api api;

    @When("^User sends GET request to endpoint (.*)$")
    public void user_sends_Get_request_to_endpoint_http_services_groupkt_com_country(String endpoint)  {
        Response response = api.getGetResponse(endpoint);
        GlobalVariables.setObjectVariable("response", response);
    }

    @Then("^User receives response with the following data$")
    public void user_receives_response_with_the_following_data(DataTable restResponse) {
        RestResponse expectedRestResponse = Inputs.getRestResponseFromDataTable(restResponse);
        Response response = (Response)GlobalVariables.getObjectVariable("response");
        RestResponse actualRestResponse = Inputs.getRestResponseFromResponse(response);
        Assert.assertEquals("RestResponse objects should be equal", expectedRestResponse, actualRestResponse);
    }

    @When("^User sends POST request to endpoint (.*) with the body$")
    public void user_sends_POST_request_to_endpoint_with_the_body(String endpointUrl, DataTable body)  {
        String key = null;
        String value = null;
        for (Map<String, String> data : body.asMaps(String.class, String.class)) {
                key = data.get("key");
                value = data.get("value");
        }
        Response response = Api.getPostResponse(endpointUrl, String.format("{`%s`:`%s`}", key, value).replace('`','"'));
        GlobalVariables.setObjectVariable("response2", response);
    }

    @Then("^Response contains response code (\\d+) and the following data$")
    public void response_contains_response_code_and_the_following_data(int expectedStatusCode, DataTable body)  {
        String key = null;
        String value = null;
        String expectedJSON;
        Response response = (Response) GlobalVariables.getObjectVariable("response2");
        for (Map<String, String> data : body.asMaps(String.class, String.class)) {
            key = data.get("key");
            value = data.get("value");
        }
        expectedJSON = Inputs.twoStringToJson(key, value);
        System.out.println("!!!! - " + expectedJSON);
        Assert.assertEquals("Response codes should be equal", expectedStatusCode, response.getStatusCode());
       // Assert.assertThat("Response body should contain the expected JSON", response.asString(), containsString(expectedJSON));

        String s = response.asString();
        System.out.println("sssss - " + s);

        Assert.assertTrue(s.contains(expectedJSON));
    //   Assert.assertTrue(s.contains(expectedJSON));

      //  Assert.assertThat("Response body should contain the expected JSON", response.asString(), containsString(expectedJSON));
        Assert.assertThat("Response body should contain the expected JSON", response.asString(), containsString(expectedJSON));
    }
}
