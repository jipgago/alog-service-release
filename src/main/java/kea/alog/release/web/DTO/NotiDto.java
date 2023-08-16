package kea.alog.release.web.DTO;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class NotiDto {
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class MessageDto{
        private String msgContent;
        private Long userPk;

        @Builder
        public MessageDto(String msgContent, Long userPk){
            this.msgContent = msgContent;
            this.userPk = userPk;
        }
    }
}
