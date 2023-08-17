package kea.alog.release.feign;

import kea.alog.release.web.DTO.AggregatorDto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "Aggregator-Service",
        url = "${aggregator.service.url}"
)
public interface AggrFeign {
    @GetMapping("/api/aggr/projects/{projectPk}/members")
    ResponseDto<PageDto<UserResponseDto>> findMembers(@PathVariable("projectPk") Long projectPk, @RequestParam(value = "keyword", required = false) String keyword,
                                                                           @RequestParam("page") int page, @RequestParam("size") int size);
}
