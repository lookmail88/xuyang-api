package xuyang.dev.xuyangapi.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xuyang.dev.xuyangapi.argo.ArgoAppStatus;
import xuyang.dev.xuyangapi.argo.ArgoService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@CrossOrigin
public class WebController {

    private final ArgoService argoService;

    public WebController(ArgoService argoService) {
        this.argoService = argoService;
    }

    @GetMapping(value="/sayhello")
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello,This is for start_2");
    }
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss_SSS");


    @GetMapping(value="/health")
    public ResponseEntity<String> getHealth(){
        ZonedDateTime nowLA = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));
        return ResponseEntity.ok( nowLA.format(formatter));
    }

    @GetMapping(value="/argo/apps")
    public ResponseEntity<List<ArgoAppStatus>> getArgoApps() {
        return ResponseEntity.ok(argoService.getCachedApps());
    }

}
