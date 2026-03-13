import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;

public class SimpleApiTest {
    @Test
    public void testGetPost() {
        RestAssured
            .get("https://jsonplaceholder.typicode.com/posts/1")
            .then()
            .statusCode(200)
            .body("title", notNullValue());
    }
}