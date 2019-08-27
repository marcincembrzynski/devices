package cembrzynski.service;


import cembrzynski.model.Device;
import cembrzynski.model.DeviceSearchResult;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

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

    private SearchQuery searchQuery;
    private List<Device> devices;

    @Mock
    Page<Device> page;

    @Before
    public void before(){
        underTest = new DeviceService(devicesDataImporter, devicesRepository);
        searchQuery = new NativeSearchQuery(QueryBuilders.simpleQueryStringQuery("foo"));
        Device device = Device.builder().uid("foo").model("bar").build();
        devices = Collections.singletonList(device);
    }

    @Test
    public void shouldSearchForDevice(){

        DeviceSearchResult expected = DeviceSearchResult.builder().result(devices).build();
        when(devicesRepository.search(Mockito.eq(searchQuery))).thenReturn(page);
        when(page.getContent()).thenReturn(devices);

        DeviceSearchResult actual = underTest.searchForDevice("foo");

        Assert.assertEquals(expected, actual);
    }
}
