package lt.mknyga.textbooks.controller;

import lt.mknyga.textbooks.dto.TextbookDetailDTO;
import lt.mknyga.textbooks.dto.TextbookListDTO;
import lt.mknyga.textbooks.model.Textbook;
import lt.mknyga.textbooks.service.TextbookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/textbooks")
public class TextbookController {
    private final TextbookService textbookService;

    public TextbookController(TextbookService textbookService) {
        this.textbookService = textbookService;
    }

    @GetMapping
    public ResponseEntity<List<TextbookListDTO>> getTextbooks(
            @RequestParam Integer grade,
            @RequestParam String subject) {
        return ResponseEntity.ok(textbookService.findByGradeAndSubject(grade, subject));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TextbookDetailDTO> getTextbook(
            @PathVariable String id,
            @RequestParam(defaultValue = "false") boolean includeTopics) {
        return ResponseEntity.ok(textbookService.findById(id, includeTopics));
    }

    @PostMapping
    public ResponseEntity<TextbookDetailDTO> createTextbook(@RequestBody Textbook textbook) {
        return new ResponseEntity<>(textbookService.create(textbook), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TextbookDetailDTO> updateTextbook(
            @PathVariable String id,
            @RequestBody Textbook textbook) {
        return ResponseEntity.ok(textbookService.update(id, textbook));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTextbook(@PathVariable String id) {
        textbookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
