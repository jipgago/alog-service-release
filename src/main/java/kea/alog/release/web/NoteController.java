package kea.alog.release.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import kea.alog.release.config.Result;
import kea.alog.release.service.NoteService;
import kea.alog.release.web.DTO.NoteDTO;
import kea.alog.release.web.DTO.NoteDTO.RspNoteListDTO;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/note")
public class NoteController {

    final private NoteService noteService;

    @GetMapping("/{notePk}")
    public ResponseEntity<Result> getNote(@PathVariable Long notePk){
        NoteDTO.SendNoteDTO note = noteService.getNote(notePk);
        if(note.ischkData()){
            Result result = Result.builder()
                            .isSuccess(true)
                            .data(note)
                            .message("Success Load")
                            .build();
        return ResponseEntity.ok().body(result);
        } else{
            Result result = Result.builder()
                            .isSuccess(false)
                            .message("Failed Load")
                            .build();
            return ResponseEntity.badRequest().body(result);
        }
    }

    @GetMapping("/list/{pjId}/{currentPage}")
    public ResponseEntity<Result> getAllNote(@PathVariable("pjId") Long pjId, @PathVariable("currentPage") Long currentPage){
        Result result;
        if(currentPage <= 0L){
            result = Result.builder()
                        .isSuccess(false)
                        .message("Bad Request")
                        .build();
            return ResponseEntity.badRequest().body(result);
        } else {
            RspNoteListDTO responseNote = noteService.getAllNote(pjId, currentPage);
            result = Result.builder()
                            .isSuccess(true)
                            .message("Success Load NoteList")
                            .data(responseNote)
                            .build();
            return ResponseEntity.ok().body(result);
        }
    }

    @PostMapping("/createNote")
    public ResponseEntity<Result> createNote(@RequestBody NoteDTO.CreateNoteDTO request){
        if(request.ischkData()){
            Long id = noteService.createNote(request);
            Result result = Result.builder()
                                .isSuccess(true)
                                .message("Success Saved.")
                                .data(id)
                                .build();
            return ResponseEntity.ok().body(result);
        } else{
            Result result = Result.builder()
                                .isSuccess(false)
                                .message("Failed Saved")
                                .build();
            return ResponseEntity.badRequest().body(result);
        }
    }

    @PutMapping("/updateNote")
    public ResponseEntity<Result> updateNote(@RequestBody NoteDTO.UpdateNoteDTO request){
        boolean chkSave = noteService.updatNote(request);
        if(chkSave){
            Result result = Result.builder()
                            .isSuccess(true)
                            .message("Success Update Note.")
                            .build();
            return ResponseEntity.ok().body(result);
        }
        else {
            Result result = Result.builder()
                            .isSuccess(false)
                            .message("Failed Update Note.")
                            .build();
            return ResponseEntity.badRequest().body(result);
        }
    }

    @DeleteMapping("/deleteNote/{noteId}")
    public ResponseEntity<Result> deleteNote(@PathVariable Long noteId){
        noteService.deleteNote(noteId);
        Result result = Result.builder()
                            .isSuccess(true)
                            .message("노트가 삭제되었습니다.")
                            .build();
        return ResponseEntity.ok().body(result);
    }
}
