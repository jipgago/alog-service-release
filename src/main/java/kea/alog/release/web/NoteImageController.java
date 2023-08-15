package kea.alog.release.web;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import kea.alog.release.config.Result;
import kea.alog.release.service.NoteImageService;
import kea.alog.release.web.DTO.NoteImageDTO.TransNoteImageDTO;
import kea.alog.release.web.DTO.NoteImageDTO.UpdateNoteImageDTO;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/release/note-image")
public class NoteImageController {
    final private NoteImageService noteImageService;

    @Operation(summary = "이미지 가져오기", description = "이미지 아이디로 이미지를 가져옵니다.")
    @GetMapping("/get/{imageId}")
    public ResponseEntity<Result> getNoteImage(@PathVariable Long imageId){
        TransNoteImageDTO rspDto = noteImageService.getImage(imageId);
        if(rspDto.chkData()){
            Result result = Result.builder()
                                .isSuccess(true)
                                .message("Success bring image")
                                .data(rspDto)
                                .build();
            return ResponseEntity.ok().body(result);
        } else {
            Result result = Result.builder()
                                .isSuccess(false)
                                .message("Failed bring image")
                                .build();
            return ResponseEntity.badRequest().body(result);
        }
    }

    @Operation(summary = "이미지 추가", description = "이미지를 추가합니다.")
    @PostMapping("/add")
    public ResponseEntity<Result> createNoteImage(@RequestBody TransNoteImageDTO reqDto){
        if(reqDto.chkData()){
            Long noteImageId = noteImageService.createImage(reqDto);
            if(noteImageId > 0L){
                Result result = Result.builder()
                                .isSuccess(true)
                                .message("Success Generated")
                                .data(noteImageId)
                                .build();
                return ResponseEntity.ok().body(result);
            } else{
                Result result = Result.builder()
                                .isSuccess(false)
                                .message("Fail Generated")
                                .build();
                return ResponseEntity.badRequest().body(result);
            }
        } else{
            Result result = Result.builder()
                                .isSuccess(false)
                                .message("Bad Request Value")
                                .build();
            return ResponseEntity.badRequest().body(result);
        }
    }

    @Operation(summary = "이미지 변경", description = "이미지를 변경합니다.")
    @PutMapping("/update")
    public ResponseEntity<Result> updateNoteImage(@RequestBody UpdateNoteImageDTO reqDto){
        UpdateNoteImageDTO rspDto = noteImageService.updateImage(reqDto);
        if(rspDto.chkData()){
            Result result = Result.builder()
                            .isSuccess(true)
                            .message("Success updated")
                            .data(rspDto)
                            .build();
            return ResponseEntity.ok().body(result);
        } else{
            Result result = Result.builder()
                            .isSuccess(false)
                            .message("Fail updated")
                            .build();
            return ResponseEntity.badRequest().body(result);
        }
    }
    @Operation(summary = "삭제", description = "이미지를 삭제합니다.")
    @DeleteMapping("/delete/{imageId}")
    public ResponseEntity<Result> deleteNoteImage(@PathVariable Long imageId){
        if(noteImageService.deleteImage(imageId)){
            Result result = Result.builder()
                            .isSuccess(true)
                            .message("Success deleted")
                            .build();
            return ResponseEntity.ok().body(result);
        } else{
            Result result = Result.builder()
                            .isSuccess(false)
                            .message("Not Found Id")
                            .build();
            return ResponseEntity.badRequest().body(result);
        }
    }
}
