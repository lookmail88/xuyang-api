package xuyang.dev.xuyangapi.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@CrossOrigin
public class WebController {

    @GetMapping(value="/sayhello")
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello,This is for my Argo");
    }
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss_SSS");

    @GetMapping(value="/health")
    public ResponseEntity<String> getHealth(){
        LocalDateTime now = LocalDateTime.now();
        return ResponseEntity.ok( now.format(formatter));
    }

}
