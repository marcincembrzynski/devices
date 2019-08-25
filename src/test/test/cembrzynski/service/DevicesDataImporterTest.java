package cembrzynski.service;

import cembrzynski.model.Device;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DevicesDataImporterTest {

    private DevicesDataImporter underTest;

    @Before
    public void before() {
        underTest = new DevicesDataImporter();
    }

    @Test
    public void shouldGet() throws IOException {

        List<Device> actual = underTest.get("src/main/resources/devices.csv");
        assertEquals(18, actual.size());
        assertEquals("a999b4ba-7d3e-5b8d-c885-280ac2221ebd", actual.get(0).getUid());
        assertEquals("sas", actual.get(0).getProfile());

    }
}
