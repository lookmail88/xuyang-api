package xuyang.dev.xuyangapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class XuyangApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(XuyangApiApplication.class, args);
    }

}
