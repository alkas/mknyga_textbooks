package lt.mknyga.textbooks.service.impl;

import lt.mknyga.textbooks.dto.SectionDTO;
import lt.mknyga.textbooks.dto.TextbookListDTO;
import lt.mknyga.textbooks.dto.TopicDetailDTO;
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
    public TopicDetailDTO findById(String id) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Topic not found"));

        Section section = sectionRepository.findBySectionId(topic.getSectionId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Section not found"));

        Textbook textbook = textbookRepository.findByTextbookId(section.getTextbookId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Textbook not found"));

        return convertToDetailDTO(topic, section, textbook);
    }

    @Override
    public TopicDetailDTO create(Topic topic) {
        Section section = sectionRepository.findBySectionId(topic.getSectionId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Section not found"));

        Textbook textbook = textbookRepository.findByTextbookId(section.getTextbookId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Textbook not found"));

        Topic savedTopic = topicRepository.save(topic);
        return convertToDetailDTO(savedTopic, section, textbook);
    }

    @Override
    public TopicDetailDTO update(String id, Topic topic) {
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
        return convertToDetailDTO(updatedTopic, section, textbook);
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
        dto.setId(topic.getId());
        dto.setTitle(topic.getTitle());
        dto.setOrderNo(topic.getOrderNo());
        dto.setPages(topic.getPages());
        dto.setPractice(topic.getPractice());
        dto.setLessons(topic.getLessons());
        return dto;
    }

    private TopicDetailDTO convertToDetailDTO(Topic topic, Section section, Textbook textbook) {
        TopicDetailDTO dto = new TopicDetailDTO();
        // Basic topic fields
        dto.setId(topic.getId());
        dto.setTitle(topic.getTitle());
        dto.setOrderNo(topic.getOrderNo());
        dto.setPages(topic.getPages());
        dto.setPractice(topic.getPractice());
        dto.setLessons(topic.getLessons());

        // Additional fields
        dto.setAchievements(topic.getAchievements());
        dto.setCompetencies(topic.getCompetencies());
        dto.setCriteria(topic.getCriteria());
        dto.setMk(topic.getMk());
        dto.setTasks(topic.getTasks());

        // Convert materials
        if (topic.getMaterials() != null) {
            dto.setMaterials(topic.getMaterials().stream()
                    .map(this::convertToMaterialDTO)
                    .collect(Collectors.toList()));
        }

        // Set section
        SectionDTO sectionDTO = new SectionDTO();
        sectionDTO.setId(section.getId());
        sectionDTO.setSectionId(section.getSectionId());
        sectionDTO.setTitle(section.getTitle());
        dto.setSection(sectionDTO);

        // Set textbook
        TextbookListDTO textbookDTO = new TextbookListDTO();
        textbookDTO.setId(textbook.getId());
        textbookDTO.setTextbookId(textbook.getTextbookId());
        textbookDTO.setTitle(textbook.getTitle());
        textbookDTO.setSlug(textbook.getSlug());
        textbookDTO.setGrade(textbook.getGrade());
        textbookDTO.setSubject(textbook.getSubject());
        textbookDTO.setPublished(textbook.getPublished());
        dto.setTextbook(textbookDTO);

        return dto;
    }

    private TopicDetailDTO.MaterialDTO convertToMaterialDTO(Topic.Material material) {
        TopicDetailDTO.MaterialDTO dto = new TopicDetailDTO.MaterialDTO();
        dto.setType(material.getType());
        dto.setDesc(material.getDesc());
        dto.setResources(material.getResources());
        return dto;
    }
}
