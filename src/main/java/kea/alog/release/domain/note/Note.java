package kea.alog.release.domain.note;

import java.beans.JavaBean;
import java.io.Serializable;

import org.springframework.stereotype.Component;

import jakarta.persistence.*;
import kea.alog.release.util.BaseTimeEntity;
import lombok.*;

@Component
@Entity
@Table(name = "note")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@JavaBean
public class Note extends BaseTimeEntity implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "r_pk")
    private Long notePk;

    @Column(name = "p_pk")//project PK
    private Long pjPk;

    @Column(name = "team_pk") //Team PK
    private Long teamPk;

    @Column(name = "note_title", length = 100)
    private String noteTitle;

    @Column(name = "note_content", length = 1000)
    private String noteContent;

    @Column(name = "note_version", length = 10)
    private String noteVersion;

    @Builder(toBuilder = true)
    public Note(Long pjPk, Long teamPk, String noteTitle, String noteContent, String noteVersion){
        this.pjPk = pjPk;
        this.teamPk = teamPk;
        this.noteTitle = noteTitle;
        this.noteContent = noteContent;
        this.noteVersion = noteVersion;
    }
}
