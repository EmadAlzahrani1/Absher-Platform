import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
public class NewClass {
    
    public static void main(String[] args) {
        
    }
    
    
}




// File: NationalResilienceSimulation.java

enum ServiceStatus {
    HEALTHY,
    DEGRADED,
    DOWN,
    RECOVERING
}

enum IncidentType {
    DDOS_ATTACK,
    DATABASE_FAILURE,
    PROVIDER_OUTAGE,
    CREDENTIAL_THEFT
}

// Represents a problem happening in the system
class Incident {
    private final IncidentType type;
    private final int severity; // 1–10

    public Incident(IncidentType type, int severity) {
        this.type = type;
        this.severity = severity;
    }

    public IncidentType getType() {
        return type;
    }

    public int getSeverity() {
        return severity;
    }

    @Override
    public String toString() {
        return type + " (severity " + severity + ")";
    }
}

// Interface for any resilient national service
interface ResilientService {
    void recognize(Incident incident);
    void resist(Incident incident);
    void recover(Incident incident);
    void reinstate();
}

// Our example: Absher-like national platform
class NationalPlatformService implements ResilientService {
    private final String name;
    private ServiceStatus status;

    public NationalPlatformService(String name) {
        this.name = name;
        this.status = ServiceStatus.HEALTHY;
    }

    @Override
    public void recognize(Incident incident) {
        System.out.println("👁️ RECOGNITION: " + name +
                " detected incident: " + incident);
        // Simple logic: high severity makes system degraded or down
        if (incident.getSeverity() >= 8) {
            status = ServiceStatus.DOWN;
        } else {
            status = ServiceStatus.DEGRADED;
        }
        System.out.println("   → Status changed to: " + status);
    }

    @Override
    public void resist(Incident incident) {
        System.out.println("🛡️ RESISTANCE: " + name +
                " is applying controls against: " + incident.getType());

        switch (incident.getType()) {
            case DDOS_ATTACK -> System.out.println("   - Enabling WAF rules and rate limiting.");
            case DATABASE_FAILURE -> System.out.println("   - Switching to read replica and isolating broken node.");
            case PROVIDER_OUTAGE -> System.out.println("   - Failing over to backup provider / region.");
            case CREDENTIAL_THEFT -> System.out.println("   - Revoking tokens and enforcing re-authentication.");
        }

        // Resistance may improve from DOWN → DEGRADED
        if (status == ServiceStatus.DOWN) {
            status = ServiceStatus.DEGRADED;
            System.out.println("   → Impact reduced. Status now: " + status);
        }
    }

    @Override
    public void recover(Incident incident) {
        System.out.println("🚑 RECOVERY: " + name +
                " is restoring critical services first...");
        System.out.println("   - Restoring login, identity, and core transactions.");
        status = ServiceStatus.RECOVERING;
        System.out.println("   → Status changed to: " + status);
    }

    @Override
    public void reinstate() {
        System.out.println("🔧 REINSTATEMENT: " + name +
                " is fully restoring all services.");
        System.out.println("   - Validating data integrity and logs.");
        System.out.println("   - Bringing back non-critical services and reports.");
        status = ServiceStatus.HEALTHY;
        System.out.println("   → System back to: " + status);
    }

    public ServiceStatus getStatus() {
        return status;
    }
}

// Simple class that runs a scenario using the 4 Rs
 class NationalResilienceSimulation {

    public static void main(String[] args) {
        NationalPlatformService absher =
                new NationalPlatformService("Absher National Platform");

        // Example incidents
        Incident ddos = new Incident(IncidentType.DDOS_ATTACK, 9);
        Incident dbFailure = new Incident(IncidentType.DATABASE_FAILURE, 6);

        System.out.println("=== Scenario 1: Large DDoS Attack ===");
        runScenario(absher, ddos);

        System.out.println("\n=== Scenario 2: Database Failure ===");
        runScenario(absher, dbFailure);
    }

    // Simulate the full 4R flow for one incident
    private static void runScenario(NationalPlatformService service, Incident incident) {
        service.recognize(incident);   // 1) detect
        service.resist(incident);      // 2) reduce impact
        service.recover(incident);     // 3) restore critical
        service.reinstate();           // 4) full restoration
        System.out.println("Final status: " + service.getStatus());
    }
}



  
    

