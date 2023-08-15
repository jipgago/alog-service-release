package kea.alog.release.web;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kea.alog.release.config.Result;
import kea.alog.release.service.TagService;
import kea.alog.release.web.DTO.TagDTO.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/release/tag")
@RequiredArgsConstructor
public class TagController {
    final TagService tagService;

    @Operation(summary = "태그 불러오기", description = "태그를 한개 상세조회합니다.")
    @GetMapping("/{tagId}")
    public ResponseEntity<Result> getTag(@PathVariable Long tagId){
        TagContentDTO tag = tagService.getTag(tagId);
        if(tag.isChkData()){
            Result result = Result.builder().isSuccess(true)
                            .message("Loaded Tag")
                            .data(tag).build();
            return ResponseEntity.ok().body(result);
        } else{
            Result result = Result.builder().isSuccess(false)
                            .message(" Load Failed Tag")
                            .build();
            return ResponseEntity.badRequest().body(result);
        }
    }

    @Operation(summary = "태그리스트", description = "태그 리스트를 10개 불러옵니다.")
    @GetMapping("/list/{currentPage}")
    public ResponseEntity<Result> getTagList(@PathVariable Long currentPage){
        TagPageingDTO rspDTO = tagService.getAllList(currentPage);
        // List<TagPrameterDTO> rspTagList = tagService.getAllList(currentPage);
        Result result = Result.builder()
                            .data(rspDTO)
                            .isSuccess(true)
                            .message("Loaded TagList")
                            .build();
        return ResponseEntity.ok().body(result);
    }

    @Operation(summary = "태그 만들기", description = "태그 만들기")
    @PostMapping("/create")
    public ResponseEntity<Result> createTag(@RequestBody TagContentDTO request){
        if(request.getTagContent() != null){
            Long tagId = tagService.createTag(request);
            Result result = Result.builder()
                                .isSuccess(true)
                                .message("Tag Saved")
                                .data(tagId).build();
            return ResponseEntity.ok().body(result);
        } else {
            Result result = Result.builder()
                                .isSuccess(false)
                                .message("Empty Contents")
                                .build();
            return ResponseEntity.badRequest().body(result);
        }
        
    }
    @Operation(summary = "태그 업데이트", description = "태그의 변경사항을 저장합니다.")
    @PutMapping("/update/{tagId}")
    public ResponseEntity<Result> updateTag(@RequestBody TagContentDTO contentDTO, @PathVariable Long tagId){
        Result result;
        if(tagService.updateTag(contentDTO, tagId)){
            result = Result.builder().data(tagId).isSuccess(true).message("Updated Tag.").build();
            return ResponseEntity.ok().body(result);
        } else {
            result = Result.builder().data(tagId).isSuccess(false).message("Failed update.").build();
            return ResponseEntity.badRequest().body(result);
        }
    }

    @Operation(summary = "태그를 삭제합니다", description = "태그를 삭제합니다.")
    @DeleteMapping("/delete/{tagId}")
    public ResponseEntity<Result> deleteTag(@PathVariable Long tagId){
        tagService.deleteTag(tagId);
        Result result = Result.builder()
                            .isSuccess(true)
                            .message("Delete Tag")
                            .build();
        return ResponseEntity.ok().body(result);
    }

}
