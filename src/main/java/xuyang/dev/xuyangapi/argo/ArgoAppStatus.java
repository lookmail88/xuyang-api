package xuyang.dev.xuyangapi.argo;

public record ArgoAppStatus(
        String name,
        String syncStatus,
        String healthStatus,
        String lastSyncTime
) {}
