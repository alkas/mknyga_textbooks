package lt.mknyga.textbooks.controller;

import lt.mknyga.textbooks.dto.TextbookDTO;
import lt.mknyga.textbooks.model.Textbook;
import lt.mknyga.textbooks.service.TextbookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TextbookController {
    private final TextbookService textbookService;

    public TextbookController(TextbookService textbookService) {
        this.textbookService = textbookService;
    }

    @GetMapping("/textbooks")
    public ResponseEntity<List<TextbookDTO>> getTextbooks(
            @RequestParam Integer grade,
            @RequestParam String subject) {
        return ResponseEntity.ok(textbookService.findByGradeAndSubject(grade, subject));
    }

    @GetMapping("/textbooks_by_grade/{grade}")
    public ResponseEntity<List<TextbookDTO>> getTextbooksByGrade(
            @PathVariable Integer grade) {
        return ResponseEntity.ok(textbookService.findByGrade(grade));
    }

    @GetMapping("/textbooks_by_subject/{subject}")
    public ResponseEntity<List<TextbookDTO>> getTextbooksBySubject(
            @PathVariable String subject) {
        return ResponseEntity.ok(textbookService.findBySubject(subject));
    }

    @GetMapping("/textbooks_by_slug/{slug}")
    public ResponseEntity<List<TextbookDTO>> getTextbooksBySlug(
            @PathVariable String slug) {
        return ResponseEntity.ok(textbookService.findBySlug(slug));
    }

    @GetMapping("/textbook/{textbookId}")
    public ResponseEntity<TextbookDTO> getTextbook(
            @PathVariable Integer textbookId,
            @RequestParam(defaultValue = "false") boolean includeTopics) {
        return ResponseEntity.ok(textbookService.findByTextbookId(textbookId, includeTopics));
    }

    @PostMapping("/textbooks")
    public ResponseEntity<TextbookDTO> createTextbook(@RequestBody Textbook textbook) {
        return new ResponseEntity<>(textbookService.create(textbook), HttpStatus.CREATED);
    }

    @PutMapping("/textbooks/{id}")
    public ResponseEntity<TextbookDTO> updateTextbook(
            @PathVariable String id,
            @RequestBody Textbook textbook) {
        return ResponseEntity.ok(textbookService.update(id, textbook));
    }

    @DeleteMapping("textbooks/{id}")
    public ResponseEntity<Void> deleteTextbook(@PathVariable String id) {
        textbookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
