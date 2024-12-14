package apiTests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class getApiCall {

    @Test
    public void getUsersApiTest() throws IOException {

        Playwright playwright = Playwright .create();
       APIRequest request = playwright.request();
       APIRequestContext requestContext = request.newContext();
       APIResponse apiResponse = requestContext.get("https://reqres.in/api/users?page=2");
       int statusCode = apiResponse.status();
        System.out.println("Response Status Code ist: " + statusCode);
        Assertions.assertEquals(200, statusCode);
        String statusText = apiResponse.statusText();
        System.out.println(statusText);
        System.out.println("[URL: " + apiResponse.url() + "]");



        ObjectMapper objectMapper = new ObjectMapper();   //Jackson databind: convert the body to a json file
        JsonNode jsonResponse = objectMapper.readTree(apiResponse.body());
        System.out.println(jsonResponse.toPrettyString());

    }
}
