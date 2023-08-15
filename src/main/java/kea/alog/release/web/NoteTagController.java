package kea.alog.release.web;

import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/api/release/noteTag")
public class NoteTagController {
    final NoteTagService noteTagService;

    @Operation(summary = "노트 태그 추가", description = "노트 태그를 추가합니다.")
    @PostMapping("/add")
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

    @Operation(summary = "태그삭제", description = "태그를 삭제합니다.")
    @DeleteMapping("/delete/{tagId}")
    public ResponseEntity<Result> deleteTag(@PathVariable Long tagId){
        noteTagService.deleteTag(tagId);
        Result result = Result.builder()
                            .isSuccess(true)
                            .message("노트태그가 삭제되었습니다.")
                            .build();
        return ResponseEntity.ok().body(result);
    }

}
