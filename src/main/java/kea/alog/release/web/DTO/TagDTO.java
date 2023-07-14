package kea.alog.release.web.DTO;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TagDTO {
    @Getter
    @NoArgsConstructor
    public static class TagContentDTO{
        private String tagContent;

        @Builder
        public TagContentDTO(String tagContent){
            this.tagContent = tagContent;
        }

        public Boolean isChkData(){
            return this.tagContent != null;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class TagPrameterDTO{
        private Long tagPk;
        private String tagContent;

        @Builder
        public TagPrameterDTO(Long tagPk, String tagContent){
            this.tagContent = tagContent;
            this.tagPk = tagPk;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class TagPageingDTO{
        private List<TagPrameterDTO> tagList;
        private Integer totalPage;

        @Builder
        public TagPageingDTO(List<TagPrameterDTO> tagList, Integer totalPage){
            this.tagList = tagList;
            this.totalPage = totalPage;
        }
    }

}
