package xuyang.dev.xuyangapi.argo;

import io.swagger.v3.oas.annotations.media.Schema;

public record ArgoAppStatus(
        @Schema(example = "xuyang-api") String name,
        @Schema(example = "xuyang") String namespace,
        @Schema(example = "Synced") String syncStatus,
        @Schema(example = "Healthy") String healthStatus,
        @Schema(example = "2026-03-29T22:02:42Z") String lastSyncTime
) {}
