package lt.mknyga.textbooks.controller;

import lt.mknyga.textbooks.dto.SectionDTO;
import lt.mknyga.textbooks.model.Section;
import lt.mknyga.textbooks.service.SectionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sections")
public class SectionController {
    private final SectionService sectionService;

    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @GetMapping("/textbook/{textbookId}")
    public ResponseEntity<List<SectionDTO>> getSectionsByTextbook(
            @PathVariable Integer textbookId) {
        return ResponseEntity.ok(sectionService.findByTextbookId(textbookId));
    }

    @PostMapping
    public ResponseEntity<SectionDTO> createSection(@RequestBody Section section) {
        return new ResponseEntity<>(sectionService.create(section), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SectionDTO> updateSection(
            @PathVariable String id,
            @RequestBody Section section) {
        return ResponseEntity.ok(sectionService.update(id, section));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSection(@PathVariable String id) {
        sectionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
