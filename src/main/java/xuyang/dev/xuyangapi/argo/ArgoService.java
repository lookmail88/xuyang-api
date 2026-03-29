package xuyang.dev.xuyangapi.argo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class ArgoService {

    private static final Logger log = LoggerFactory.getLogger(ArgoService.class);

    private static final String ARGO_API = "https://argo.xuyang.dev/api/v1/applications";

    @Value("${argo.api.token}")
    private String apiToken;

    private final HttpClient httpClient = HttpClient.newBuilder().build();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final AtomicReference<List<ArgoAppStatus>> cache = new AtomicReference<>(Collections.emptyList());

    @Scheduled(fixedRate = 60000, initialDelay = 0)
    public void refresh() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(ARGO_API))
                    .header("Authorization", "Bearer " + apiToken)
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            JsonNode root = objectMapper.readTree(response.body());
            JsonNode items = root.path("items");

            List<ArgoAppStatus> result = new ArrayList<>();
            for (JsonNode item : items) {
                String name = item.path("metadata").path("name").asText();
                String syncStatus = item.path("status").path("sync").path("status").asText();
                String healthStatus = item.path("status").path("health").path("status").asText();
                String lastSyncTime = item.path("status").path("operationState").path("finishedAt").asText();
                result.add(new ArgoAppStatus(name, syncStatus, healthStatus, lastSyncTime));
            }
            cache.set(Collections.unmodifiableList(result));
        } catch (Exception e) {
            log.error("Failed to fetch Argo apps: {}", e.getMessage(), e);
        }
    }

    public List<ArgoAppStatus> getCachedApps() {
        return cache.get();
    }
}
