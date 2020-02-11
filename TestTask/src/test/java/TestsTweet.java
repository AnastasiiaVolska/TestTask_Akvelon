import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class TestsTweet {

    Logger logger = LogManager.getLogger(TestsTweet.class);

    @BeforeClass
    public static void initSpec() {
        RestAssured.baseURI = "https://api.twitter.com/1.1/statuses/";
    }

    @Test
    public void WriteTweet() {

        Autozisation autozisation = new Autozisation();
        String str = UUID.randomUUID().toString();
        Response res = given().auth().oauth(autozisation.consumerKey, autozisation.consumerSecret, autozisation.accessToken, autozisation.tokenSecret)
                .when()
                .post("update.json?status=" + str)
                .then()
                .statusCode(200)
                .body("text", equalTo(str))
                .extract()
                .response();
        logger.info(res.asString());
    }

    @Test
    public void assertSearchById() {

        Autozisation autozisation = new Autozisation();
        String id = "210462857140252672";
        Response res = given().auth().oauth(autozisation.consumerKey, autozisation.consumerSecret, autozisation.accessToken, autozisation.tokenSecret)
                .when()
                .get("show.json?id="+id)
                .then()
                .statusCode(200)
                .body("id_str", equalTo(id))
                .extract()
                .response();
        logger.info(res.asString());
    }

}
