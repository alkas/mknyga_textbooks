package lt.mknyga.textbooks.service.impl;

import lt.mknyga.textbooks.dto.TopicDTO;
import lt.mknyga.textbooks.dto.TopicListDTO;
import lt.mknyga.textbooks.model.Section;
import lt.mknyga.textbooks.model.Textbook;
import lt.mknyga.textbooks.model.Topic;
import lt.mknyga.textbooks.repository.SectionRepository;
import lt.mknyga.textbooks.repository.TextbookRepository;
import lt.mknyga.textbooks.repository.TopicRepository;
import lt.mknyga.textbooks.service.TopicService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicServiceImpl implements TopicService {
    private final TopicRepository topicRepository;
    private final SectionRepository sectionRepository;
    private final TextbookRepository textbookRepository;

    public TopicServiceImpl(TopicRepository topicRepository,
                            SectionRepository sectionRepository,
                            TextbookRepository textbookRepository) {
        this.topicRepository = topicRepository;
        this.sectionRepository = sectionRepository;
        this.textbookRepository = textbookRepository;
    }

    @Override
    public List<TopicListDTO> findByTextbookId(Integer textbookId) {
        if (!textbookRepository.existsByTextbookId(textbookId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Textbook not found");
        }
        return topicRepository.findByTextbookId(textbookId)
                .stream()
                .map(this::convertToListDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TopicListDTO> findBySectionId(Integer sectionId) {
        if (!sectionRepository.existsBySectionId(sectionId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Section not found");
        }
        return topicRepository.findBySectionId(sectionId)
                .stream()
                .map(this::convertToListDTO)
                .collect(Collectors.toList());
    }
    @Override
    public TopicDTO findByTopicId(Integer topicId) {
        //System.out.println("Getting topic by id: " + id);
        Topic topic = topicRepository.findByTopicId(topicId)
                .orElseThrow(() -> {
                    System.out.println("Topic npot found by id: " + topicId);
                    return new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Topic not found");
                        });

        Section section = sectionRepository.findBySectionId(topic.getSectionId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Section not found"));

        Textbook textbook = textbookRepository.findByTextbookId(section.getTextbookId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Textbook not found"));

        return convertToDTO(topic, section, textbook);
    }

    @Override
    public TopicDTO create(Topic topic) {
        Section section = sectionRepository.findBySectionId(topic.getSectionId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Section not found"));

        Textbook textbook = textbookRepository.findByTextbookId(section.getTextbookId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Textbook not found"));

        Topic savedTopic = topicRepository.save(topic);
        return convertToDTO(savedTopic, section, textbook);
    }

    @Override
    public TopicDTO update(String id, Topic topic) {
        if (!topicRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Topic not found");
        }

        Section section = sectionRepository.findBySectionId(topic.getSectionId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Section not found"));

        Textbook textbook = textbookRepository.findByTextbookId(section.getTextbookId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Textbook not found"));

        topic.setId(id);
        Topic updatedTopic = topicRepository.save(topic);
        return convertToDTO(updatedTopic, section, textbook);
    }

    @Override
    public void delete(String id) {
        if (!topicRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Topic not found");
        }
        topicRepository.deleteById(id);
    }

    private TopicListDTO convertToListDTO(Topic topic) {
        TopicListDTO dto = new TopicListDTO();
        // Basic topic fields
        dto.setId(topic.getId());
        dto.setTopicId(topic.getTopicId());
        dto.setTitle(topic.getTitle());
        dto.setPages(topic.getPages());
        dto.setPractice(topic.getPractice());
        dto.setLessons(topic.getLessons());
        return dto;
    }

    private TopicDTO convertToDTO(Topic topic, Section section, Textbook textbook) {
        TopicDTO dto = new TopicDTO();
        // Basic topic fields
        dto.setId(topic.getId());
        dto.setTopicId(topic.getTopicId());
        dto.setTextbookId(topic.getTextbookId());
        dto.setSectionId(topic.getSectionId());
        dto.setTitle(topic.getTitle());
        dto.setPages(topic.getPages());
        dto.setPractice(topic.getPractice());
        dto.setLessons(topic.getLessons());
        dto.setAchievements(topic.getAchievements());
        dto.setCompetencies(topic.getCompetencies());
        dto.setCriteria(topic.getCriteria());
        dto.setTasks(topic.getTasks());

        // Convert materials
        if (topic.getMaterials() != null) {
            dto.setMaterials(topic.getMaterials().stream()
                    .map(this::convertToMaterialDTO)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    private TopicDTO.MaterialDTO convertToMaterialDTO(Topic.Material material) {
        TopicDTO.MaterialDTO dto = new TopicDTO.MaterialDTO();
        dto.setType(material.getType());
        dto.setDesc(material.getDesc());
        dto.setResources(material.getResources());
        return dto;
    }


}
