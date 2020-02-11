import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

public class TestSearch {

    Logger logger = LogManager.getLogger(TestSearch.class);

    @BeforeClass
    public static void initSpec() {
        RestAssured.baseURI = "https://api.twitter.com/1.1/search/";
    }


    @Test
    public void assertSearchByText() {
        Autozisation autozisation = new Autozisation();
        String firstElement = "0";
        String query = "Akvelon";
        String resultType = "recent";
        int count = 1;
        Response res = given().auth().oauth(autozisation.consumerKey, autozisation.consumerSecret, autozisation.accessToken, autozisation.tokenSecret)
                .when()
                .get("tweets.json?q=" + query + "&result_type=" + resultType + "&count=" + count)
                .then()
                .statusCode(200)
                .body(String.format("statuses.text[%s]", firstElement), containsString(query))
                .extract()
                .response();
        logger.info(res.asString());
    }
}
