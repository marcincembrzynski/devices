package cembrzynski.service;

import cembrzynski.model.Device;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface DevicesRepository extends ElasticsearchRepository<Device, String> {

}