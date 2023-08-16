package kea.alog.release.service;

import kea.alog.release.web.DTO.NotiDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "notice-service",
        url = "${notice.service.url}"
)
public interface NotiFeign {
    @PostMapping("/api/noti")
    String create(@RequestBody NotiDto.MessageDto message);
}
