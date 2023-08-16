package kea.alog.release.web.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class AggregatorDto {
    @Builder
    @Getter
    public static class ResponseDto<T> {
        private final int code;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private final String message;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private T data;

        public static ResponseDto success(int code) {
            return ResponseDto.builder().code(code).build();
        }

        public static <T> ResponseDto<T> success(int code, T data) {
            return ResponseDto.<T>builder().code(code).data(data).build();
        }

        public static ResponseDto fail(int code, String message) {
            return ResponseDto.builder()
                    .code(code)
                    .message(message)
                    .build();
        }
    }
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UserResponseDto {
        private Long userPk;
        private String email;
        private String userNN;
        private String profile;
    }
    @Getter
    @Builder
    public static class PageDto<T> {
        private int totalPages;
        private long totalElements;
        private int pageNumber;
        private int pageSize;
        private List<T> content;
    }

}
