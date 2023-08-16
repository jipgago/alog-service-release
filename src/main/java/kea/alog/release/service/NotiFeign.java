package kea.alog.release.service;

import kea.alog.release.web.DTO.NotiDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "notice-service",
        url = "${notification.service.url}"
)
public interface NotiFeign {
    @PostMapping("/api/noti")
    public String create(@RequestBody NotiDto.MessageDto message);
}
