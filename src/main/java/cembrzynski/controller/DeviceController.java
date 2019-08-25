package cembrzynski.controller;

import cembrzynski.model.DeviceSearchResult;
import cembrzynski.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping("/api/devices")
    public ResponseEntity<DeviceSearchResult> search(@RequestParam(name = "query", required = false) String query){
        return ResponseEntity.ok(deviceService.searchForDevice(query));
    }

}
