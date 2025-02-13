package lt.mknyga.textbooks.service.impl;

import lt.mknyga.textbooks.dto.TopicDTO;
import lt.mknyga.textbooks.dto.TopicListDTO;
import lt.mknyga.textbooks.model.Material;
import lt.mknyga.textbooks.model.Section;
import lt.mknyga.textbooks.model.Textbook;
import lt.mknyga.textbooks.model.Topic;
import lt.mknyga.textbooks.repository.MaterialRepository;
import lt.mknyga.textbooks.repository.SectionRepository;
import lt.mknyga.textbooks.repository.TextbookRepository;
import lt.mknyga.textbooks.repository.TopicRepository;
import lt.mknyga.textbooks.service.TopicService;
import lt.mknyga.textbooks.util.DTOConverter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TopicServiceImpl implements TopicService {
    private final TopicRepository topicRepository;
    private final SectionRepository sectionRepository;
    private final TextbookRepository textbookRepository;
    private final MaterialRepository materialRepository;
    private final DTOConverter dtoConverter;


    public TopicServiceImpl(TopicRepository topicRepository,
                            SectionRepository sectionRepository,
                            TextbookRepository textbookRepository,
                            MaterialRepository materialRepository,
                            DTOConverter dtoConverter) {
        this.topicRepository = topicRepository;
        this.sectionRepository = sectionRepository;
        this.textbookRepository = textbookRepository;
        this.materialRepository = materialRepository;
        this.dtoConverter = dtoConverter;
    }

    @Override
    public List<TopicListDTO> findByTextbookId(Integer textbookId) {
        if (!textbookRepository.existsByTextbookId(textbookId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Textbook not found");
        }
        return topicRepository.findAllByTextbookId(textbookId)
                .stream()
                .map(dtoConverter::convertToTopicListDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TopicListDTO> findBySectionId(Integer sectionId) {
        if (!sectionRepository.existsBySectionId(sectionId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Section not found");
        }
        return topicRepository.findAllBySectionId(sectionId)
                .stream()
                .map(dtoConverter::convertToTopicListDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TopicDTO findByTopicId(Integer topicId) {
        Topic topic = topicRepository.findByTopicId(topicId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Topic not found"));

        Section section = sectionRepository.findBySectionId(topic.getSectionId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Section not found"));

        Textbook textbook = textbookRepository.findByTextbookId(topic.getTextbookId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Textbook not found"));

        List<Material> materials = materialRepository.findAllByTopicId(topicId);

        return dtoConverter.convertToTopicDTO(topic, materials, section, textbook);
    }

    @Override
    public TopicDTO create(Topic topic) {

        if (topicRepository.existsByTopicId(topic.getTopicId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Topic with this id already exists");
        }
        Topic savedTopic = topicRepository.save(topic);
        return dtoConverter.convertToTopicDTO(savedTopic, null, null, null);
    }

    @Override
    public TopicDTO update(String id, Topic updatedTopic) {

        Topic existingTopic = topicRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Topic not found"));

        if (updatedTopic.getTopicId() != null) {
            existingTopic.setTopicId(updatedTopic.getTopicId());
        }
        if (updatedTopic.getSectionId() != null) {
            existingTopic.setSectionId(updatedTopic.getSectionId());
        }
        if (updatedTopic.getTextbookId() != null) {
            existingTopic.setTextbookId(updatedTopic.getTextbookId());
        }
        if (updatedTopic.getTitle() != null) {
            existingTopic.setTitle(updatedTopic.getTitle());
        }
        if (updatedTopic.getPages() != null) {
            existingTopic.setPages(updatedTopic.getPages());
        }
        if (updatedTopic.getPractice() != null) {
            existingTopic.setPractice(updatedTopic.getPractice());
        }
        if (updatedTopic.getLessons() != null) {
            existingTopic.setLessons(updatedTopic.getLessons());
        }
        if (updatedTopic.getAchievements() != null) {
            existingTopic.setAchievements(updatedTopic.getAchievements());
        }
        if (updatedTopic.getCompetencies() != null) {
            existingTopic.setCompetencies(updatedTopic.getCompetencies());
        }
        if (updatedTopic.getCriteria() != null) {
            existingTopic.setCriteria(updatedTopic.getCriteria());
        }
        if (updatedTopic.getTasks() != null) {
            existingTopic.setTasks(updatedTopic.getTasks());
        }

        Topic savedTopic = topicRepository.save(existingTopic);
        return dtoConverter.convertToTopicDTO(savedTopic, null, null, null);
    }

    @Override
    public void delete(String id) {
        if (!topicRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Topic not found");
        }
        topicRepository.deleteById(id);
    }

}
