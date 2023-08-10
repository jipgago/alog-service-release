package kea.alog.release.web;

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
