/*
 Purpose : Program for Test Cases is Written to Test Spotify Api
 @author Dinesh Kumar Peddakotla
 @since 23-05-2021
*/
package com.spotifyapi;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class TestSpotifyApi {
    /** varaibles are declared and initialized */
    public String token = "";
    String userId;
    /** declared trackIds string array and initialized */
    String[] trackIds = {"0VnEZ9XMrhMu8OCDnRBoEL", "1pvuGKdtbTuSuEXdryvOeS", "0dnDTvdUco2UbaBjUtPxNS"};
    /** declared showIds string array and initialized */
    public String[] showIds = { "6ZcvVBPQ2ToLXEWVbaw59P", "2X40qLyoj1wQ2qE5FVpA7x"};
    /** declared playlistId string array and initialized */
    String[] playlistId = {"1z9iy3sQdnEHkPbDZn9ARj", "4OzZWovLLaob2dHyyWzBzw", "4tXVHLlsnDrkSEjRxYsRg4"};

    /**
     * setup method is created to set token for authentication
     */
    @BeforeTest
    public void setup() {
        token = "Bearer BQBZiFoZQ31DVECpndiatDhR13Mm55LApp7Bjolo72zGzsiC" +
                "pqEqfmuKbewUNceCR4UysK0RZ1x5m5zyt7B-_reieK6Oh7uagVlcTxu" +
                "nyHHN59mmzWSLOKC-BHvyE_6SUcYNS6RHuw4oD3rlZwNXV4siEUYWBv" +
                "F4IeUDYX5Vpu3ZA0b1ulAZ8ptstdoJS0hsZOJm6rUFM92VCZ_dUVLkk" +
                "eU98CZUBLeZKZNwa3Q2N8vWnuqL2N--cX572CeRmwuw3nypUsUXnlta" +
                "ovqujpFUUATk4x4SDamciG6fJ4gm05q9";
    }

    /**
     * test case is written to test current user profile  api and to check status code 200
     * for GET operation
     */
    @Test
    public void testGET_CurrentUserProfile_ShouldReturn_StatusCode200() {
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
    public void testGET_UserProfile_ShouldReturn_StatusCode200() {
        Response response = given().contentType(ContentType.JSON)
                                   .accept(ContentType.JSON)
                                   .header("Authorization",token)
                                   .get("https://api.spotify.com/v1/users/"+userId);

        response.prettyPrint();
        response.then().statusCode(200);
    }

    /**
     *test case is written to test audio analysis for a track api and to check status code 200
     * for GET operation
     */
    @Test
    public void testGET_AudioAnalysis_ForATrack_ShouldReturn_StatusCode200() {
        Response response = given().contentType(ContentType.JSON)
                                   .accept(ContentType.JSON)
                                   .header("Authorization", token)
                                   .get("https://api.spotify.com/v1/audio-analysis/"+ trackIds[0]);
        response.prettyPrint();
        response.then().statusCode(200);
    }

    /**
     *test case is written to test audio features for several tracks api and to check status code 200
     * for GET operation
     */
    @Test
    public void testGET_AudioFeatures_ForSeveral_Tracks_ShouldReturn_StatusCode200() {
        Response response = given().contentType(ContentType.JSON)
                                   .accept(ContentType.JSON)
                                   .header("Authorization", token)
                                   .get("https://api.spotify.com/v1/audio-features?ids="+Arrays.toString(trackIds));
        response.prettyPrint();
        response.then().statusCode(200);
    }

    /**
     *test case is written to test audio features for a track api and to check status code 200
     * for GET operation
     */
    @Test
    public void testGET_AudioFeatures_ForA_Tracks_ShouldReturn_StatusCode200() {
        Response response = given().contentType(ContentType.JSON)
                                   .accept(ContentType.JSON)
                                   .header("Authorization", token)
                                   .get("https://api.spotify.com/v1/audio-features/"+trackIds[0]);

        response.prettyPrint();
        response.then().statusCode(200);
    }

    /**
     *test case is written to test several tracks api and to check status code 200
     * for GET operation
     */
    @Test
    public void testGET_SeveralTracks_ShouldReturn_StatusCode200() {
        Response response = given().contentType(ContentType.JSON)
                                   .accept(ContentType.JSON)
                                   .header("Authorization", token)
                                   .get("https://api.spotify.com/v1/tracks?ids="+ trackIds[0]+"&market=IN");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    /**
     *test case is written to test track api and to check status code 200
     * for GET operation
     */
    @Test
    public void testGET_ATrack_ShouldReturn_StatusCode200() {
        Response response = given().contentType(ContentType.JSON)
                                   .accept(ContentType.JSON)
                                   .header("Authorization", token)
                                   .get("https://api.spotify.com/v1/tracks/"+trackIds[0]+"?market=IN");

        response.prettyPrint();
        response.then().statusCode(200);
    }


    /**
     *test case is written to test several shows api and to check status code 200
     * for GET operation
     */
    @Test
    public void testGET_SeveralShows_ShouldReturn_StatusCode200() {
        Response response = given().queryParam("ids", (Object[]) showIds)
                                   .contentType(ContentType.JSON)
                                   .accept(ContentType.JSON)
                                   .header("Authorization", token)
                                   .get("https://api.spotify.com/v1/shows");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    /**
     *test case is written to test show episodes api and to check status code 200
     * for GET operation
     */
    @Test
    public void testGET_ShowEpisodes_ShouldReturn_StatusCode200() {
        Response response = given().queryParam("market", "IN")
                                   .queryParam("limit", "3")
                                   .queryParam("offset", "3")
                                   .contentType(ContentType.JSON)
                                   .accept(ContentType.JSON)
                                   .header("Authorization", token)
                                   .get("https://api.spotify.com/v1/shows/"+showIds[0]+"/episodes");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    /**
     *test case is written to test show api and to check status code 200
     * for GET operation
     */
    @Test
    public void test_GetAShow_ShouldReturn_StatusCode200() {
        Response response = given().queryParam("market", "IN")
                                   .contentType(ContentType.JSON)
                                   .accept(ContentType.JSON)
                                   .header("Authorization", token)
                                   .get("https://api.spotify.com/v1/shows/"+showIds[1]);

        response.prettyPrint();
        response.then().statusCode(200);
    }
    /**
     *test case is written to test search api and to check status code 200
     * for GET operation
     */
    @Test
    public void testGET_WhenSearch_ForAnItem_ShouldReturn_StatusCode200() {
        Response response = given().queryParam("q", "Uppena")
                                   .queryParam("type", "album")
                                   .queryParam("market", "IN")
                                   .contentType(ContentType.JSON)
                                   .accept(ContentType.JSON)
                                   .header("Authorization", token)
                                   .get("https://api.spotify.com/v1/search");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    /**
     *test case is written to test remove item in playlist api and to check status code 200
     * for DELETE operation
     */
    @Test
    public void testDELETE_RemoveItemsFromAPlaylist_Should_ReturnStatusCode200() {
        //declared map object and initialized with hashmap
        Map<String,Object> mapUri = new HashMap<>();
        mapUri.put("uri", "spotify:track:5nVFeACm96rucybpDAjfK5");

        //declared and initialized array object
        ArrayList<Object> tracks = new ArrayList<>();
        tracks.add(mapUri);

        Map<String,Object> map = new HashMap<>();
        map.put("tracks", tracks);

        //created json object
        JSONObject body = new JSONObject(map);

        Response response = given().contentType(ContentType.JSON)
                                   .accept(ContentType.JSON)
                                   .header("Authorization", token)
                                   .body(body.toJSONString())
                                   .delete("https://api.spotify.com/v1/playlists/" + playlistId[0] +"/tracks");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    /**
     *test case is written to test create a new playlist api and to check status code 201
     * for POST operation
     */
    @Test
    public void testPOST_When_CreateAPlaylist_Should_ReturnStatusCode201() {
        //declared map object and initialized with hashmap
        Map<String,Object> map = new HashMap<>();

        map.put("name", "Latest Track");
        map.put("description", "Latest Songs");
        map.put("public", false);

        //created json object
        JSONObject body = new JSONObject(map);

        Response response = given().contentType(ContentType.JSON)
                                   .accept(ContentType.JSON)
                                   .header("Authorization", token)
                                   .body(body)
                                   .post("https://api.spotify.com/v1/users/"+userId+"/playlists");

        response.prettyPrint();
        response.then().statusCode(201);
    }

    /**
     *test case is written to test added item to a playlist api and to check status code 201
     * for POST operation
     */
    @Test
    public void testPOST_WhenAdded_Items_toAPlaylist_ShouldReturn_StatusCode201() {
        String[] uris = {"spotify:track:6rcTPn1CwOyuskilY12IqL","spotify:track:74IQCxI4nws964fic1Q4pv"};

        //declared map object and initialized with hashmap
        Map<String,Object> map = new HashMap<>();
        map.put("uris", uris);

        //created json object
        JSONObject body = new JSONObject(map);

        Response response = given().contentType(ContentType.JSON)
                                   .contentType(ContentType.JSON)
                                   .header("Authorization", token)
                                   .body(body)
                                   .post("https://api.spotify.com/v1/playlists/"+playlistId[1]+"/tracks");

        response.prettyPrint();
        response.then().statusCode(201);
    }

    /**
     *test case is written to test upload custom playlist cover image api and to check status code 202
     * for PUT operation
     */
    @Test
    public void testPUT_WhenUploadA_CustomPlaylist_CoverImage_ShouldReturn_StatusCode202() {
        //declared variable and initialized file path
        String imagePath = "C:/Users/dinnu/Downloads/download.txt";
        //new file created
        File file = new File(imagePath);

        Response response = given().contentType(ContentType.TEXT)
                                   .accept(ContentType.TEXT)
                                   .header("Authorization", token)
                                   .body(file)
                                   .put("https://api.spotify.com/v1/playlists/"+playlistId[1]+"/images");

        response.prettyPrint();
        response.then().statusCode(202);
    }

    /**
     *test case is written to test reorder or replace api and to check status code 201
     * for PUT operation
     */
    @Test
    public void testPUT_WhenReorder_Or_ReplaceA_PlaylistItems_ShouldReturn_StatusCode200_Or_201() {
        //declared map object and initialized with hashmap
        Map<String,Object> map = new HashMap<>();
        map.put("range_start",1);
        map.put( "insert_before",3);
        map.put( "range_length",2);

        //created json object
        JSONObject body = new JSONObject(map);

        Response response = given().queryParam("uris", "spotify:track:74IQCxI4nws964fic1Q4pv")
                                   .contentType(ContentType.JSON)
                                   .accept(ContentType.JSON)
                                   .header("Authorization", token)
                                   .body(body)
                                   .put("https://api.spotify.com/v1/playlists/"+playlistId[2]+"/tracks");

        response.prettyPrint();
        response.then().statusCode(201);
    }

    /**
     *test case is written to test change playlist details api and to check status code 200
     * for PUT operation
     */
    @Test
    public void testPUT_WhenChange_APlaylistDetails_ShouldReturn_StatusCode200() {
        //declared map object and initialized with hashmap
        Map<String,Object> map = new HashMap<>();
        map.put("name", "Latest Songs");
        map.put("description", "Latest Songs");
        map.put("public", false);

        //created json object
        JSONObject body = new JSONObject(map);

        Response response = given().contentType(ContentType.JSON)
                                   .accept(ContentType.JSON)
                                   .header("Authorization", token)
                                   .body(body)
                                   .put("https://api.spotify.com/v1/playlists/"+playlistId[2]);

        response.prettyPrint();
        response.then().statusCode(200);
    }

    /**
     *test case is written to test list of current users playlist api and to check status code 200
     * for GET operation
     */
    @Test
    public void testGet_AListOf_CurrentUsers_Playlist_ShouldReturn_StatusCode200() {
        Response response = given().queryParam("limit", 5)
                                   .queryParam("offset", 6)
                                   .contentType(ContentType.JSON)
                                   .accept(ContentType.JSON)
                                   .header("Authorization", token)
                                   .get("https://api.spotify.com/v1/me/playlists");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    /**
     *test case is written to test get playlist cover image api and to check status code 200
     * for GET operation
     */
    @Test
    public void testGet_APlaylist_CoverImage_ShouldReturn_StatusCode200() {
        Response response = given().contentType(ContentType.JSON)
                                   .accept(ContentType.JSON)
                                   .header("Authorization", token)
                                   .get("https://api.spotify.com/v1/playlists/"+playlistId[2]+"/images");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    /**
     *test case is written to test get playlist items api and to check status code 200
     * for GET operation
     */
    @Test
    public void testGet_APlaylistItems_ShouldReturn_StatusCode200() {
        Response response = given().queryParam("market", "IN")
                                   .queryParam("fields", "items(added_by.id,track(name,href,album(name,href)))")
                                   .queryParam("limit", 10)
                                   .queryParam("offset", 5)
                                   .contentType(ContentType.JSON)
                                   .accept(ContentType.JSON)
                                   .header("Authorization", token)
                                   .get("https://api.spotify.com/v1/playlists/"+playlistId[2]+"/tracks");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    /**
     *test case is written to test get playlist api and to check status code 200
     * for GET operation
     */
    @Test
    public void testGET_APlaylist_ShouldReturn_StatusCode200() {
        Response response = given().queryParam("market", "IN")
                                   .contentType(ContentType.JSON)
                                   .accept(ContentType.JSON)
                                   .header("Authorization", token)
                                   .get(" https://api.spotify.com/v1/playlists/"+playlistId[2]);

        response.prettyPrint();
        response.then().statusCode(200);
    }

    /**
     *test case is written to test list of users playlist api and to check status code 200
     * for GET operation
     */
    @Test
    public void testGetA_ListOfA_UsersPlaylist_ShouldReturn_StatusCode200() {
        Response response = given().contentType(ContentType.JSON)
                                   .accept(ContentType.JSON)
                                   .header("Authorization", token)
                                   .get("https://api.spotify.com/v1/users/"+userId+"/playlists");

        response.prettyPrint();
        response.then().statusCode(200);
    }
}
