package xuyang.dev.xuyangapi.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @GetMapping(value="/sayhello")
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello,This is for my Argo");
    }
}
