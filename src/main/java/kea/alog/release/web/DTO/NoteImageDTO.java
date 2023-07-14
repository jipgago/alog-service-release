package kea.alog.release.web.DTO;

import lombok.*;

public class NoteImageDTO {

    @Getter
    @NoArgsConstructor
    public static class TransNoteImageDTO{
        private Long notePk;
        private Long filePk;

        @Builder
        public TransNoteImageDTO(long notePk, long filePk){
            this.notePk = notePk;
            this.filePk = filePk;
        }

        public boolean chkData(){
            return this.notePk != null || this.filePk != null || this.notePk > 0 || this.filePk > 0;
        }
    }
    @Getter
    @NoArgsConstructor
    public static class UpdateNoteImageDTO{
        private Long imageId;
        private Long notePk;
        private Long filePk;

        @Builder
        public UpdateNoteImageDTO(Long imageId, Long notePk, Long filePk){
            this.imageId = imageId;
            this.notePk = notePk;
            this.filePk = filePk;
        }

        public boolean chkData(){
            return this.imageId != null || this.notePk != null || this.filePk != null;
        }
    }   
}
