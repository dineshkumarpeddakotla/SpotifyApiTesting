/*
 Purpose : Program for Test Cases is Written to Test Spotify User Profile Api
 @author Dinesh Kumar Peddakotla
 @since 23-05-2021
*/
package com.spotifyapi;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestUserProfileApi {

    /**varaibles are declared and initialized */
    public static String token = setup();
    String userId;

    /**
     * setup method is created to set token for authentication
     * @return token for authentication
     */
    public static String setup() {
        token = "Bearer BQBZiFoZQ31DVECpndiatDhR13Mm55LApp7Bjolo72zGzsiC" +
                "pqEqfmuKbewUNceCR4UysK0RZ1x5m5zyt7B-_reieK6Oh7uagVlcTxu" +
                "nyHHN59mmzWSLOKC-BHvyE_6SUcYNS6RHuw4oD3rlZwNXV4siEUYWBv" +
                "F4IeUDYX5Vpu3ZA0b1ulAZ8ptstdoJS0hsZOJm6rUFM92VCZ_dUVLkk" +
                "eU98CZUBLeZKZNwa3Q2N8vWnuqL2N--cX572CeRmwuw3nypUsUXnlta" +
                "ovqujpFUUATk4x4SDamciG6fJ4gm05q9";
        return token;
    }

    /**
     * test case is written to test current user profile  api and to check status code 200
     * for GET operation
     */
    @Test
    public void testGET_01_CurrentUserProfile_ShouldReturn_StatusCode200() {
        Response response = given().contentType(ContentType.JSON)
                                   .accept(ContentType.JSON).header("Authorization", token )
                                   .get("https://api.spotify.com/v1/me");

        userId = response.then().extract().path("id");
        System.out.println("User Id : "+userId);
        response.prettyPrint();
        response.then().statusCode(200);
    }

    /** test case is written to test user profile  api and to check status code 200
     *for GET operation
     */
    @Test
    public void testGET_02_UserProfile_ShouldReturn_StatusCode200() {
        Response response = given().contentType(ContentType.JSON)
                                   .accept(ContentType.JSON)
                                   .header("Authorization",token)
                                   .get("https://api.spotify.com/v1/users/"+userId);

        response.prettyPrint();
        response.then().statusCode(200);
    }
}
