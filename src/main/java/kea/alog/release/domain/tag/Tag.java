package kea.alog.release.domain.tag;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import jakarta.persistence.*;
import kea.alog.release.util.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Component
@Entity
@Table(name = "tag")
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Getter
public class Tag extends BaseTimeEntity implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_pk")
    private Long tagPk;

    @Column(name = "tag_content", length = 20)
    private String tagContent;

    @Builder(toBuilder = true)
    public Tag(Long tagPk, String tagContent){
        this.tagPk = tagPk;
        this.tagContent = tagContent;
    }
}
