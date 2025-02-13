package lt.mknyga.textbooks.service.impl;

import lt.mknyga.textbooks.dto.MaterialDTO;
import lt.mknyga.textbooks.model.Material;
import lt.mknyga.textbooks.repository.MaterialRepository;
import lt.mknyga.textbooks.repository.TopicRepository;
import lt.mknyga.textbooks.service.MaterialService;
import lt.mknyga.textbooks.util.DTOConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class MaterialServiceImpl implements MaterialService {
    private static final Logger LOG = LoggerFactory.getLogger(MaterialServiceImpl.class);

    private final MaterialRepository materialRepository;
    private final TopicRepository topicRepository;
    private final DTOConverter dtoConverter;

    public MaterialServiceImpl(MaterialRepository materialRepository,
                               TopicRepository topicRepository,
                               DTOConverter dtoConverter) {
        this.materialRepository = materialRepository;
        this.topicRepository = topicRepository;
        this.dtoConverter = dtoConverter;
    }

    @Override
    public List<MaterialDTO> findAll() {
        return materialRepository.findAll()
                .stream()
                .map(dtoConverter::convertToMaterialDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MaterialDTO> findAllByTopicId(Integer topicId) {
        if (!topicRepository.existsByTopicId(topicId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Topic not found");
        }
        try {
            LOG.info("Getting all materials for topic with givven topicId:{}", topicId);
            return materialRepository.findAllByTopicId(topicId)
                    .stream()
                    .map(dtoConverter::convertToMaterialDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error retrieving materials for topic: " + topicId);
        }
    }

    @Override
    public List<MaterialDTO> createBatch(List<Material> materials) {
        // Validate that all materials have valid topic IDs
        Set<Integer> TopicIds = materials.stream()
                .map(Material::getTopicId)
                .collect(Collectors.toSet());

        // Check if all topics exist
        for (Integer topicId : TopicIds) {
            if (!topicRepository.existsByTopicId(topicId)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Topic with id %s not found", topicId));
            }
        }

        // Save all materials
        List<Material> savedMaterials = materialRepository.saveAll(materials);

        // Convert and return DTOs
        return savedMaterials.stream()
                .map(dtoConverter::convertToMaterialDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MaterialDTO update(String id, Material updatedMaterial) {

        Material existingMaterial = materialRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Material not found"));

        if (updatedMaterial.getTopicId() != null) {
            existingMaterial.setTopicId(updatedMaterial.getTopicId());
        }
        if (updatedMaterial.getType() != null) {
            existingMaterial.setType(updatedMaterial.getType());
        }
        if  (updatedMaterial.getDescription() != null) {
            existingMaterial.setDescription(updatedMaterial.getDescription());
        }
        if (updatedMaterial.getResource() != null) {
            existingMaterial.setResource(updatedMaterial.getResource());
        }

        Material savedMaterial = materialRepository.save(existingMaterial);
        return dtoConverter.convertToMaterialDTO(savedMaterial);
    }

    @Override
    public void delete(String id) {
        if (!materialRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Material not found");
        }
        materialRepository.deleteById(id);
    }
 }
