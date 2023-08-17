package kea.alog.release.web;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import kea.alog.release.config.Result;
import kea.alog.release.service.NoteService;
import kea.alog.release.web.DTO.NoteDTO;
import kea.alog.release.web.DTO.NoteDTO.RspNoteListDTO;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/release/note")
public class NoteController {

    final private NoteService noteService;

    @Operation(summary = "릴리즈 노트 상세조회", description = "릴리즈 노트 상세 조회")
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
    @Operation(summary = "프로젝트 릴리즈 노트 가져오기", description = "프로젝트 안에 있는 릴리즈 노트 페이징으로 가져오기")
    @GetMapping("/list/{pjId}/{currentPage}/{pageSet}")
    public ResponseEntity<Result> getAllNote(@PathVariable("pjId") Long pjId, @PathVariable("currentPage") Long currentPage, @PathVariable("pageSet") Long pageSet){
        Result result;
        if(currentPage <= 0L){
            result = Result.builder()
                        .isSuccess(false)
                        .message("Bad Request")
                        .build();
            return ResponseEntity.badRequest().body(result);
        } else {
            RspNoteListDTO responseNote = noteService.getAllNote(pjId, currentPage, pageSet);
            result = Result.builder()
                            .isSuccess(true)
                            .message("Success Load NoteList")
                            .data(responseNote)
                            .build();
            return ResponseEntity.ok().body(result);
        }
    }

    @Operation(summary = "릴리즈 노트 만들기", description = "릴리즈 노트 생성")
    @PostMapping("/create")
    public ResponseEntity<Result> createNote(@RequestHeader("Authorization") String jwt, @RequestBody NoteDTO.CreateNoteDTO request){
        if(request.ischkData()){
            Long id = noteService.createNote(request, jwt);
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

    @Operation(summary = "릴리즈 노트 업데이트", description = "릴리즈 노트 업데이트")
    @PutMapping("/update/{notePk}")
    public ResponseEntity<Result> updateNote(@RequestHeader("Authorization") String jwt ,@RequestBody NoteDTO.UpdateNoteDTO request){
        boolean chkSave = noteService.updatNote(request, jwt);
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
    @Operation(summary = "릴리즈 노트 지우기", description = "릴리즈 노트 지우기")
    @DeleteMapping("/delete/{noteId}")
    public ResponseEntity<Result> deleteNote(@PathVariable Long noteId){
        noteService.deleteNote(noteId);
        Result result = Result.builder()
                            .isSuccess(true)
                            .message("노트가 삭제되었습니다.")
                            .build();
        return ResponseEntity.ok().body(result);
    }
}
