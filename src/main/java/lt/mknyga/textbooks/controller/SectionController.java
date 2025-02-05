package lt.mknyga.textbooks.controller;

import lt.mknyga.textbooks.dto.ApiResponse;
import lt.mknyga.textbooks.dto.SectionDTO;
import lt.mknyga.textbooks.model.Section;
import lt.mknyga.textbooks.service.SectionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SectionController {
    private final SectionService sectionService;

    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @GetMapping("/textbooks/{textbookId}/sections")
    public ResponseEntity<ApiResponse<List<SectionDTO>>> getSectionsByTextbook(
            @PathVariable Integer textbookId) {
        return ResponseEntity.ok(new ApiResponse<>(sectionService.findByTextbookId(textbookId)));
    }

    @GetMapping("/sections/{sectionId}")
    public ResponseEntity<ApiResponse<SectionDTO>> getSectionBySectionId(@PathVariable Integer sectionId) {
        return ResponseEntity.ok(new ApiResponse<>(sectionService.findBySectionId(sectionId)));
    }

    @PostMapping("/sections")
    public ResponseEntity<ApiResponse<SectionDTO>> createSection(@RequestBody Section section) {
        return new ResponseEntity<>(new ApiResponse<>(sectionService.create(section)), HttpStatus.CREATED);
    }

    @PutMapping("/sections/{id}")
    public ResponseEntity<ApiResponse<SectionDTO>> updateSection(
            @PathVariable String id,
            @RequestBody Section section) {
        return ResponseEntity.ok(new ApiResponse<>(sectionService.update(id, section)));
    }

    @DeleteMapping("/sections/{id}")
    public ResponseEntity<Void> deleteSection(@PathVariable String id) {
        sectionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
