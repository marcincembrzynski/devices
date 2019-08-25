package cembrzynski.service;


import cembrzynski.model.Device;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DeviceServiceTest {

    private DeviceService underTest;
    @Mock
    private DevicesDataImporter devicesDataImporter;
    @Mock
    private DevicesRepository devicesRepository;

    @Before
    public void before(){
        underTest = new DeviceService(devicesDataImporter, devicesRepository);
    }

    @Test
    public void shouldSearchForDecvice(){
        List<Device> expected = Collections.singletonList(Device.builder().uid("foo").model("bar").build());
        when(devicesRepository.search("foo")).thenReturn(expected);

        List<Device> actual = devicesRepository.search("foo");

        Assert.assertEquals(expected, actual);
    }
}
