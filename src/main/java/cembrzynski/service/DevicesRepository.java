package cembrzynski.service;

import cembrzynski.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DevicesRepository extends JpaRepository<Device, String> {

    @Query("SELECT d FROM Device d WHERE " +
            "LOWER(d.uid) LIKE CONCAT('%', LOWER(:query), '%') OR " +
            "LOWER(d.profile) LIKE CONCAT('%', LOWER(:query), '%') OR " +
            "LOWER(d.hostname) LIKE CONCAT('%', LOWER(:query), '%') OR " +
            "LOWER(d.description) LIKE CONCAT('%', LOWER(:query), '%') OR " +
            "LOWER(d.ipAddress) LIKE CONCAT('%', LOWER(:query), '%') OR " +
            "LOWER(d.extIpAddress) LIKE CONCAT('%', LOWER(:query), '%') OR " +
            "LOWER(d.lastUser) LIKE CONCAT('%', LOWER(:query), '%') OR " +
            "LOWER(d.model) LIKE CONCAT('%', LOWER(:query), '%') OR " +
            "LOWER(d.operatingSystem) LIKE CONCAT('%', LOWER(:query), '%') OR " +
            "LOWER(d.serialNumber) LIKE CONCAT('%', LOWER(:query), '%') OR " +
            "LOWER(d.motherboard) LIKE CONCAT('%', LOWER(:query), '%') OR " +
            "LOWER(d.agentVersion) LIKE CONCAT('%', LOWER(:query), '%') OR " +
            "LOWER(d.customField1) LIKE CONCAT('%', LOWER(:query), '%') OR " +
            "LOWER(d.customField2) LIKE CONCAT('%', LOWER(:query), '%') OR " +
            "LOWER(d.customField3) LIKE CONCAT('%', LOWER(:query), '%') OR " +
            "LOWER(d.customField4) LIKE CONCAT('%', LOWER(:query), '%') OR " +
            "LOWER(d.customField5) LIKE CONCAT('%', LOWER(:query), '%')" +
            "")
    List<Device> search(@Param("query") String query);
}
