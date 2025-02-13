package lt.mknyga.textbooks.controller;

import lt.mknyga.textbooks.dto.ApiResponse;
import lt.mknyga.textbooks.dto.MaterialDTO;
import lt.mknyga.textbooks.model.Material;
import lt.mknyga.textbooks.service.MaterialService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MaterialController {
    private final MaterialService materialService;

    public MaterialController(MaterialService materialService) { this.materialService = materialService; }

    @GetMapping("/materials")
    public ResponseEntity<ApiResponse<List<MaterialDTO>>> getMaterials() {
        return ResponseEntity.ok(new ApiResponse<>(materialService.findAll()));
    }

    @GetMapping("/topics/{topicId}/materials")
    public ResponseEntity<ApiResponse<List<MaterialDTO>>> getMaterialsByTopic(@PathVariable Integer topicId) {
        return ResponseEntity.ok(new ApiResponse<>(materialService.findAllByTopicId(topicId)));
    }

    @PostMapping("/materials")
    public ResponseEntity<ApiResponse<List<MaterialDTO>>> createMaterials(@RequestBody List<Material> materials) {
        return new ResponseEntity<>(new ApiResponse<>(materialService.createBatch(materials)), HttpStatus.CREATED);
    }

    @PutMapping("/materials/{id}")
    public ResponseEntity<ApiResponse<MaterialDTO>> updateMaterial(
            @PathVariable String id,
            @RequestBody Material material) {
        return ResponseEntity.ok(new ApiResponse<>(materialService.update(id, material)));
    }

    @DeleteMapping("/materials/{id}")
    public ResponseEntity<Void> deleteMaterial(@PathVariable String id) {
        materialService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
