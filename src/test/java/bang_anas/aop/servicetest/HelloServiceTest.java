package bang_anas.aop.servicetest;

import bang_anas.aop.service.HelloService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HelloServiceTest {

    @Autowired
    private HelloService helloService;

    @Test
    void helloService(){
        Assertions.assertEquals("Hello Anas", helloService.hello("Anas"));
        Assertions.assertEquals("Bye Anas", helloService.bye("Anas"));
    }

}
