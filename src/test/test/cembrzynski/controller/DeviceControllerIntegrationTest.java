package cembrzynski.controller;

import cembrzynski.model.Device;
import cembrzynski.model.DeviceSearchResult;
import cembrzynski.service.DevicesDataImporter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeviceControllerIntegrationTest {

    @LocalServerPort
    private int port;

    private Device expectedDevice;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void before() throws IOException{
        DevicesDataImporter devicesDataImporter = new DevicesDataImporter();
        List<Device> devices = devicesDataImporter.get("src/main/resources/devices.csv");
        expectedDevice = devices.get(0);

    }

    @Test
    public void shouldSearchDevices(){

        String searchUrl = "http://localhost:" + port + "/api/devices?query=a999b4ba-7d3e-5b8d-c885-280ac2221ebd";

        ResponseEntity<DeviceSearchResult> actual = restTemplate.getForEntity(searchUrl, DeviceSearchResult.class);

        assertEquals(HttpStatus.OK, actual.getStatusCode());
        Device actualDevice = actual.getBody().getResult().get(0);
        assertEquals(expectedDevice, actualDevice);
    }
}
