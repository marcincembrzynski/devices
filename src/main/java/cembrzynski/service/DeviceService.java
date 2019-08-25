package cembrzynski.service;

import cembrzynski.model.Device;
import cembrzynski.model.DeviceSearchResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class DeviceService {

    private final DevicesDataImporter devicesDataImporter;
    private final DevicesRepository devicesRepository;
    private List<Device> devices;
    @Value("${devices.data.import}")
    private boolean isDataImport;
    @Value("${devices.data.file}")
    private String devicesDataFile;

    @Autowired
    public DeviceService(DevicesDataImporter devicesDataImporter, DevicesRepository devicesRepository) {
        this.devicesDataImporter = devicesDataImporter;
        this.devicesRepository = devicesRepository;
    }

    @PostConstruct
    private void init(){
        importData();
    }

    public DeviceSearchResult searchForDevice(String query){
        return DeviceSearchResult.builder().result(devicesRepository.search(query)).build();
    }

    private void importData() {
        if(isDataImport) {
            log.info("importing data from {}", devicesDataFile);
            try {
                devices = devicesDataImporter.get(devicesDataFile);
                devices.forEach(e -> devicesRepository.save(e));
                log.info("imported data: {}", devices);
            } catch (IOException ex) {
                throw new IllegalStateException();
            }
        }
    }
}