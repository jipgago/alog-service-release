package kea.alog.release.domain.noteTag;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import jakarta.persistence.*;
import kea.alog.release.util.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Component
@Entity
@Table(name = "category")
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class NoteTag extends BaseTimeEntity implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_tag_pk")
    private Long noteTagPk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "note_pk")
    private Long notePk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_pk")
    private Long tagPk;

    @Builder
    public NoteTag(Long notePk, Long tagPk){
        this.notePk = notePk;
        this.tagPk = tagPk;
    }
    
}
