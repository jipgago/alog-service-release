package kea.alog.release.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.*;
import kea.alog.release.domain.note.Note;
import kea.alog.release.domain.note.NoteRepository;
import kea.alog.release.domain.noteimage.NoteImage;
import kea.alog.release.domain.noteimage.NoteImageRepository;
import kea.alog.release.web.DTO.NoteImageDTO.TransNoteImageDTO;
import kea.alog.release.web.DTO.NoteImageDTO.UpdateNoteImageDTO;

@Service
@RequiredArgsConstructor
public class NoteImageService {
    final private NoteRepository noteRepository;
    final private NoteImageRepository noteImageRepository;

    /**
     * 이미지를 가져옴
     * @param imageId
     * @return filePk, notePk
     */
    @Transactional
    public TransNoteImageDTO getImage(Long imageId){
        Optional<NoteImage> optNoteImage = noteImageRepository.findById(imageId);
        if(optNoteImage.isPresent()){
            NoteImage getNoteImage = optNoteImage.get();
            return TransNoteImageDTO.builder()
                            .filePk(getNoteImage.getFilePk())
                            .notePk(getNoteImage.getNotePk().getNotePk())
                            .build();
        } else {
            return TransNoteImageDTO.builder().build();
        }
    }

    /**
     * 이미지 저장
     * @param reqDto
     * @return imageId
     */
    @Transactional
    public Long createImage(TransNoteImageDTO reqDto){
        Optional<Note> note = noteRepository.findById(reqDto.getNotePk());
        if(note.isPresent() || reqDto.getFilePk() > 0){
            NoteImage noteImg = NoteImage.builder()
                                    .notePk(note.get())
                                    .filePk(reqDto.getFilePk())
                                    .build();
            noteImageRepository.save(noteImg);
            return noteImg.getImageId();
        } else return 0L;
    }

    /**
     * 이미지 변경사항 저장
     * @param reqDto
     * @return UpdateNoteImageDto
     */
    @Transactional
    public UpdateNoteImageDTO updateImage(UpdateNoteImageDTO reqDto){
        Optional<NoteImage> noteImg = noteImageRepository.findById(reqDto.getImageId());
        Optional<Note> note = noteRepository.findById(reqDto.getNotePk());
        if(noteImg.isPresent() || note.isPresent()){
            NoteImage updateImg = noteImg.get().toBuilder()
                                            .filePk(reqDto.getFilePk())
                                            .notePk(note.get())
                                            .build();
            noteImageRepository.save(updateImg);
            return reqDto;
        } return UpdateNoteImageDTO.builder().build();
    }

    /**
     * 이미지 저장
     * @param imageId
     * @return
     */
    @Transactional
    public boolean deleteImage(Long imageId){
        Optional<NoteImage> noteImg = noteImageRepository.findById(imageId);
        if(noteImg.isPresent()){
            noteImageRepository.delete(noteImg.get());
            return true;
        } else return false;
    }
}
