package cembrzynski.service;


import cembrzynski.model.Device;
import cembrzynski.model.DeviceSearchResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DeviceServiceTest {

    private DeviceService underTest;
    @Mock
    private DevicesDataImporter devicesDataImporter;
    @Mock
    private DevicesRepository devicesRepository;
    @Mock
    Page<Device> page;
    private List<Device> devices;

    @Before
    public void before(){
        underTest = new DeviceService(devicesDataImporter, devicesRepository);
        Device device = Device.builder().uid("foo").model("bar").build();
        devices = Collections.singletonList(device);
    }

    @Test
    public void shouldSearchForDevice(){

        DeviceSearchResult expected = DeviceSearchResult.builder().result(devices).build();
        when(devicesRepository.search(any(SearchQuery.class))).thenReturn(page);
        when(page.getContent()).thenReturn(devices);

        DeviceSearchResult actual = underTest.searchForDevice("foo");

        Assert.assertEquals(expected, actual);
    }
}
