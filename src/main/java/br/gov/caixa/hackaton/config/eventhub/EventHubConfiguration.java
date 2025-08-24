package br.gov.caixa.hackaton.config.eventhub;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "eventhub")
@Data
public class EventHubConfiguration {

    private String endpoint;
    private String sharedAccessKeyName;
    private String sharedAccessKey;
    private String entityPath;

    public String getConnectionString() {
        return String.format(
                "Endpoint=%s;SharedAccessKeyName=%s;SharedAccessKey=%s;EntityPath=%s",
                endpoint, sharedAccessKeyName, sharedAccessKey, entityPath
        );
    }

}
