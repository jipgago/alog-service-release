package kea.alog.release.domain.noteTag;


import java.io.Serializable;

import org.springframework.stereotype.Component;

import kea.alog.release.domain.tag.Tag;
import jakarta.persistence.*;
import kea.alog.release.domain.note.Note;
import kea.alog.release.util.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Component
@Entity
@Table(name = "notetag")
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Getter
public class NoteTag extends BaseTimeEntity implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_tag_pk")
    private Long noteTagPk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "note_pk")
    private Note notePk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_pk")
    private Tag tagPk;

    @Builder
    public NoteTag(Long noteTagPk , Note notePk, Tag tagPk){
        this.noteTagPk = noteTagPk;
        this.notePk = notePk;
        this.tagPk = tagPk;
    }
    
}
