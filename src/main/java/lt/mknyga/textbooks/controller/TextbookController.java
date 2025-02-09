package lt.mknyga.textbooks.controller;

import lt.mknyga.textbooks.dto.ApiResponse;
import lt.mknyga.textbooks.dto.TextbookDTO;
import lt.mknyga.textbooks.dto.TextbookListDTO;
import lt.mknyga.textbooks.model.Textbook;
import lt.mknyga.textbooks.service.TextbookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TextbookController {
    private final TextbookService textbookService;

    public TextbookController(TextbookService textbookService) {
        this.textbookService = textbookService;
    }

    @GetMapping("/textbooks")
    public ResponseEntity<ApiResponse<List<TextbookListDTO>>> getTextbooks(
            @RequestParam Integer grade,
            @RequestParam String subject) {
        return ResponseEntity.ok(new ApiResponse<>(textbookService.findByGradeAndSubject(grade, subject)));
    }

    @GetMapping("/grades/{grade}/textbooks")
    public ResponseEntity<ApiResponse<List<TextbookListDTO>>> getTextbooksByGrade(
            @PathVariable Integer grade) {
        return ResponseEntity.ok(new ApiResponse<>(textbookService.findByGrade(grade)));
    }

    @GetMapping("/subjects/{subject}/textbooks")
    public ResponseEntity<ApiResponse<List<TextbookListDTO>>> getTextbooksBySubject(
            @PathVariable String subject) {
        return ResponseEntity.ok(new ApiResponse<>(textbookService.findBySubject(subject)));
    }

    @GetMapping("/slugs/{slug}/textbooks")
    public ResponseEntity<ApiResponse<List<TextbookListDTO>>> getTextbooksBySlug(
            @PathVariable String slug) {
        return ResponseEntity.ok(new ApiResponse<>(textbookService.findBySlug(slug)));
    }

    @GetMapping("/textbooks/{textbookId}")
    public ResponseEntity<ApiResponse<TextbookDTO>> getTextbook(
            @PathVariable Integer textbookId,
            @RequestParam(defaultValue = "false") boolean includeTopics) {
        return ResponseEntity.ok(new ApiResponse<>(textbookService.findByTextbookId(textbookId, includeTopics)));
    }

    @PostMapping("/textbooks")
    public ResponseEntity<ApiResponse<TextbookDTO>> createTextbook(@RequestBody Textbook textbook) {
        return new ResponseEntity<>(new ApiResponse<>(textbookService.create(textbook)), HttpStatus.CREATED);
    }

    @PutMapping("/textbooks/{id}")
    public ResponseEntity<ApiResponse<TextbookDTO>> updateTextbook(
            @PathVariable String id,
            @RequestBody Textbook textbook) {
        return ResponseEntity.ok(new ApiResponse<>(textbookService.update(id, textbook)));
    }

    @DeleteMapping("textbooks/{id}")
    public ResponseEntity<Void> deleteTextbook(@PathVariable String id) {
        textbookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
