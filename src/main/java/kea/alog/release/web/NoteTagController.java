package kea.alog.release.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kea.alog.release.config.Result;
import kea.alog.release.service.NoteTagService;
import kea.alog.release.web.DTO.NoteTagDTO;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/noteTag")
public class NoteTagController {
    final NoteTagService noteTagService;

    @PostMapping("/addTag")
    public ResponseEntity<Result> addTag(@RequestBody NoteTagDTO noteTagDTO){
        if(noteTagService.addTag(noteTagDTO)){
            Result result = Result.builder()
                                .isSuccess(true)
                                .message("노트태그가 추가되었습니다.")
                                .build();
            return ResponseEntity.ok().body(result);
        } else {
            Result result = Result.builder()
                                .isSuccess(false)
                                .message("Failed adding NoteTag")
                                .build();
            return ResponseEntity.badRequest().body(result);
        }
        
        
    }

    @DeleteMapping("/deleteTag/{tagId}")
    public ResponseEntity<Result> deleteTag(@PathVariable Long tagId){
        noteTagService.deleteTag(tagId);
        Result result = Result.builder()
                            .isSuccess(true)
                            .message("노트태그가 삭제되었습니다.")
                            .build();
        return ResponseEntity.ok().body(result);
    }

}
