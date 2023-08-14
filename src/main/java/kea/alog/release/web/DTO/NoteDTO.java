package kea.alog.release.web.DTO;

import java.time.LocalDateTime;
import java.util.List;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class NoteDTO {
    @Getter
    @NoArgsConstructor
    public static class SendNoteDTO{
        private String noteTitle;
        private String noteContent;
        private String noteVersion;
        private LocalDateTime createdDate;
        private LocalDateTime modifiedDate;


        @Builder
        public SendNoteDTO(String noteTitle, String noteContent, String noteVersion, LocalDateTime createdDate, LocalDateTime modifiedDate){
            this.noteTitle = noteTitle;
            this.noteContent = noteContent;
            this.noteVersion = noteVersion;
            this.createdDate = createdDate;
            this.modifiedDate = modifiedDate;
        }

        public boolean ischkData() {
            return this.noteTitle != null || this.noteContent != null || this.noteVersion != null;
        }
    }


    @Getter
    @NoArgsConstructor
    public static class CreateNoteDTO{
        private Long pjPk;
        private Long teamPk;
        private String noteTitle;
        private String noteContent;
        private String noteVersion;

        @Builder
        public CreateNoteDTO(Long teamPk, Long pjPk, String noteTitle, String noteContent, String noteVersion){
            this.teamPk = teamPk;
            this.pjPk = pjPk;
            this.noteTitle = noteTitle;
            this.noteContent = noteContent;
            this.noteVersion = noteVersion;
        }
        public boolean ischkData() {
            return this.noteTitle != null || this.noteContent != null || this.noteVersion != null;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class UpdateNoteDTO {
        private Long notePk;
        private Long pjPk;
        private String noteTitle;
        private String noteContent;
        private String noteVersion;

        @Builder
        public UpdateNoteDTO(Long notePk, Long pjPk, String noteTitle, String noteContent, String noteVersion){
            this.pjPk = pjPk;
            this.notePk = notePk;
            this.noteTitle = noteTitle;
            this.noteContent = noteContent;
            this.noteVersion = noteVersion;
        }
    }
    
    @Getter
    @NoArgsConstructor
    public static class NoteListDTO{
        private Long notePk;
        private Long pjPk;
        private Long teamPk;
        private String noteTitle;
        private String noteContent;
        private String noteVersion;
        private String noteFileLink;
        private LocalDateTime createDate;
        private LocalDateTime modifiedDate;

        @Builder
        public NoteListDTO(Long notePk, Long pjPk, Long teamPk, String noteTitle, String noteContent, String noteVersion, LocalDateTime createDate, LocalDateTime modifiedDate) {
            this.notePk = notePk;
            this.pjPk = pjPk;
            this.teamPk = teamPk;
            this.noteTitle = noteTitle;
            this.noteContent = noteContent;
            this.noteVersion = noteVersion;
            this.createDate = createDate;
            this.modifiedDate = modifiedDate;
        }
    }
    @Getter
    @NoArgsConstructor
    public static class RspNoteListDTO{
        private List<NoteListDTO> rspList;
        private int totalPage;

        @Builder
        public RspNoteListDTO(List<NoteListDTO> rspList, int totalPage){
            this.rspList = rspList;
            this.totalPage = totalPage;
        }
    }
}
