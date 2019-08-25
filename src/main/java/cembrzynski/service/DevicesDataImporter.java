package cembrzynski.service;

import cembrzynski.model.Device;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DevicesDataImporter {


    public List<Device> get(String fileName) throws IOException{
        InputStream inputStream = new FileSystemResource(fileName).getInputStream();
        return IOUtils.readLines(inputStream, "UTF-8").stream()
                .skip(1)
                .filter(e -> StringUtils.isNotEmpty(e))
                .map(e -> e.replaceAll("\"", ""))
                .map(e -> toDevice(e)).collect(Collectors.toList());
    }

    private Device toDevice(String value){
        String[] values = value.split(",");
        return Device.builder()
                .uid(values[0])
                .profile(values[1])
                .hostname(values[2])
                .description(values[3])
                .ipAddress(values[4])
                .extIpAddress(values[5])
                .lastUser(values[6])
                .agentVersion(values[7])
                .model(values[8])
                .operatingSystem(values[9])
                .serialNumber(values[10])
                .motherboard(values[11])
                .customField1(getDefault(values, 12))
                .customField2(getDefault(values, 13))
                .customField3(getDefault(values, 14))
                .customField4(getDefault(values, 15))
                .customField5(getDefault(values, 16))
                .build();
    }

    private String getDefault(String[] values, int index){
        return values.length > index ? values[index] : "";
    }


}
