package study.cloud_user_service.bcrypt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class passwordTest {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    void notEqualTest() throws Exception {
        // given
        String password = "123456";

        // when
        String result1 = bCryptPasswordEncoder.encode(password);
        Thread.sleep(1000);
        String result2 = bCryptPasswordEncoder.encode(password);

        // then
        assertThat(result1).isNotEqualTo(result2);
    }
}
