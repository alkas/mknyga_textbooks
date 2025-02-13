package lt.mknyga.textbooks.service;

import lt.mknyga.textbooks.dto.MaterialDTO;
import lt.mknyga.textbooks.model.Material;

import java.util.List;

public interface MaterialService {
    List<MaterialDTO> findAll();
    List<MaterialDTO> findAllByTopicId(Integer topicId);
    List<MaterialDTO> createBatch(List<Material> materials);
    MaterialDTO update(String id, Material material);
    void delete(String id);
}
