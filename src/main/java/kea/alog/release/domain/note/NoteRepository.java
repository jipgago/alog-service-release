package kea.alog.release.domain.note;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    
    // Page<Note> findAllByPjPkOrderByNotePkDesc(Long pjPk, Pageable pageable);
    Page<Note> findAllByPjPk(Long pjPk, Pageable pageable);

    List<Note> findAllByTeamPk(Long teamPk);
}
