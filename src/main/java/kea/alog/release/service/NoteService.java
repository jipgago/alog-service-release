package kea.alog.release.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import kea.alog.release.web.DTO.AggregatorDto.*;
import kea.alog.release.web.DTO.NotiDto;
import lombok.AllArgsConstructor;
import org.aspectj.bridge.Message;
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
    private NotiFeign notiFeign;
    private AggrFeign aggrFeign;
    final private NoteRepository noteRepository;

    /**
     * 노트를 저장하는 로직
     * @param request
     * @return notePk
     */
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
        /**
         * 알림 보내는 로직
         */
        ResponseDto<PageDto<UserResponseDto>> memberInfo = aggrFeign.findMembers(request.getPjPk(),null,1,100);
        List<UserResponseDto> member = memberInfo.getData().getContent();
        StringBuilder sb = new StringBuilder();
        sb.append("릴리즈 노트가 생성되었습니다.");
        for(UserResponseDto index : member){
            NotiDto.MessageDto messageDto = NotiDto.MessageDto.builder().userPk(index.getUserPk()).msgContent(sb.toString()).build();
            notiFeign.create(messageDto);
        }
        return newNote.getNotePk();
    }

    /**
     * 노트 업데이트
     * @param request
     * @return boolean
     */
    @Transactional
    public boolean updatNote(NoteDTO.UpdateNoteDTO request){
        Optional<Note> loadNote = noteRepository.findById(request.getNotePk());
        if(loadNote.isPresent() && loadNote.get().getPjPk() == request.getPjPk()){
            Note setNote = loadNote.get();
            Note saveNote;
            saveNote = setNote.toBuilder()
                            .noteTitle(request.getNoteTitle())
                            .noteContent(request.getNoteContent())
                            .noteVersion(request.getNoteVersion())
                            .build();
            noteRepository.save(saveNote);
            /**
             * 알림 보내는 로직
             */
            ResponseDto<PageDto<UserResponseDto>> memberInfo = aggrFeign.findMembers(setNote.getPjPk(),null,1,100);
            List<UserResponseDto> member = memberInfo.getData().getContent();
            StringBuilder sb = new StringBuilder();
            sb.append("릴리즈 노트가 수정되었습니다.");
            for(UserResponseDto index : member){
                NotiDto.MessageDto messageDto = NotiDto.MessageDto.builder().userPk(index.getUserPk()).msgContent(sb.toString()).build();
                notiFeign.create(messageDto);
            }
            return true;
        } else return false;
    }

    /**
     * note 전부 가져오기 페이징
     * @param pjId
     * @param currentPage
     * @param reqSize
     * @return
     */
    @Transactional
    public RspNoteListDTO getAllNote(Long pjId, Long currentPage, Long reqSize){
        //List<Note> allNote = noteRepository.findAllByPjPk(pPk);
        
        int pageSize = reqSize.intValue();//나중에 front랑 상의해서 정해야함
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

    /**
     * 노트를 상세조회
     * @param notePk
     * @return noteDto
     */
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

    /**
     * 노트를 지운다.
     * @param noteId
     */
    @Transactional
    public void deleteNote(Long noteId) {
        Optional<Note> optNote = noteRepository.findById(noteId);
        if(optNote.isPresent()){
            noteRepository.delete(optNote.get());
        }
    }
    

}
