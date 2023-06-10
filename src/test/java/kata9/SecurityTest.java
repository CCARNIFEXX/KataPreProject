package kata9;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SecurityTest {

    @LocalServerPort
    private int port;

    @ParameterizedTest
    @CsvSource(textBlock = """
                   /user,user,test,200
                   /user,admin,test,200
                   /admin,user,test,200
                   /admin,admin,test,200
                  
            """)
    public void pageShouldAnswerWithCode(String url, String username, String password, Integer code) {
        TestRestTemplate testRestTemplate = new TestRestTemplate(username, password, TestRestTemplate.HttpClientOption.ENABLE_COOKIES);
        Object loginRequest = "username=%s&password=%s".formatted(username, password);
        ResponseEntity<String> resultLogin = testRestTemplate
                .postForEntity("http://localhost:" + port + "/login", loginRequest,String.class);
        assertEquals(HttpStatus.FOUND, resultLogin.getStatusCode());
        ResponseEntity<String> page = testRestTemplate.withBasicAuth(username, password)
                .getForEntity("http://localhost:" + port + url, String.class);

        System.out.println(page.getHeaders());
        System.out.println(page.getBody());
        assertEquals(HttpStatus.OK, page.getStatusCode());
        assertFalse(Objects.requireNonNull(page.getBody()).contains("Please sign in"));


    }
}