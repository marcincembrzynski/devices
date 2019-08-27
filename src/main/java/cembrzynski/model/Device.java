package cembrzynski.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Device {

    @Id
    private String uid;
    private String profile;
    private String hostname;
    private String description;
    private String ipAddress;
    private String extIpAddress;
    private String lastUser;
    private String agentVersion;
    private String model;
    private String operatingSystem;
    private String serialNumber;
    private String motherboard;
    private String customField1;
    private String customField2;
    private String customField3;
    private String customField4;
    private String customField5;
}
