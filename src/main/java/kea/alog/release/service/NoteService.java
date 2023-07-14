package kea.alog.release.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kea.alog.release.domain.note.Note;
import kea.alog.release.domain.note.NoteRepository;
import kea.alog.release.web.DTO.NoteDTO;
import kea.alog.release.web.DTO.NoteDTO.NoteListDTO;
import kea.alog.release.web.DTO.NoteDTO.RspNoteListDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoteService {
    @Autowired
    final private NoteRepository noteRepository;

    @Transactional
    public Long createNote(NoteDTO.CreateNoteDTO request) { //Redirect를 url값들로 보내달라.
        Note newNote = Note.builder()
                            .noteTitle(request.getNoteTitle())
                            .pjPk(request.getPjPk())
                            .teamPk(request.getTeamPk())
                            .noteContent(request.getNoteContent())
                            .noteVersion(request.getNoteVersion())
                            .build();

        noteRepository.save(newNote);
        return newNote.getNotePk();
    }
    @Transactional
    public boolean updatNote(NoteDTO.UpdateNoteDTO request){
        Optional<Note> loadNote = noteRepository.findById(request.getNoteId());
        if(loadNote.isPresent() && loadNote.get().getPjPk()==request.getPjPk()){
            Note setNote = loadNote.get();
            setNote = setNote.toBuilder()
                            .noteTitle(request.getNoteTitle())
                            .noteContent(request.getNoteContent())
                            .noteVersion(request.getNoteVersion())
                            .build();
            noteRepository.save(setNote);
            return true;
        } else return false;
    }
    @Transactional
    public RspNoteListDTO getAllNote(Long pjId, Long currentPage){
        //List<Note> allNote = noteRepository.findAllByPjPk(pPk);
        
        int pageSize = 2;//나중에 front랑 상의해서 정해야함
        //int offset = (((int) currentPage)-1) * pageSize; //맵핑
        Pageable pageable = PageRequest.of( currentPage.intValue()-1, pageSize, Sort.by("notePk").descending());
        
        Page<Note> pagingNote = noteRepository.findAllByPjPk(pjId, pageable);
        List<NoteListDTO> rspList = new ArrayList<>();
        if(pagingNote.getContent().size() > 0){
            for(Note index : pagingNote.getContent()){
                NoteListDTO addNote = NoteListDTO.builder()
                                                .notePk(index.getNotePk())
                                                .pjPk(index.getPjPk())
                                                .teamPk(index.getTeamPk())
                                                .noteTitle(index.getNoteTitle())
                                                .noteContent(index.getNoteContent())
                                                .noteVersion(index.getNoteVersion())
                                                .createDate(index.getCreatedDate())
                                                .modifiedDate(index.getModifiedDate())
                                                .build();
                rspList.add(addNote);
            }
        }
        RspNoteListDTO rspNoteListDTO = RspNoteListDTO.builder()
                                                    .rspList(rspList)
                                                    .totalPage(pagingNote.getTotalPages())
                                                    .build();
        return rspNoteListDTO;
    }
    @Transactional
    public NoteDTO.SendNoteDTO getNote(Long notePk){
        Optional<Note> optNote = noteRepository.findById(notePk);
        if(optNote.isPresent()){
            Note note = optNote.get();
            NoteDTO.SendNoteDTO noteDTO = NoteDTO.SendNoteDTO.builder()
                                    .noteTitle(note.getNoteTitle())
                                    .noteContent(note.getNoteContent())
                                    .noteVersion(note.getNoteVersion())
                                    .createdDate(note.getCreatedDate())
                                    .modifiedDate(note.getModifiedDate())
                                    .build();
            return noteDTO;
        } return null;
    }

    @Transactional
    public void deleteNote(Long noteId) {
        Optional<Note> optNote = noteRepository.findById(noteId);
        if(optNote.isPresent()){
            noteRepository.delete(optNote.get());
        }
    }
    

}
