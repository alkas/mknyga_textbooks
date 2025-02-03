package lt.mknyga.textbooks.service.impl;

import lt.mknyga.textbooks.dto.SectionDTO;
import lt.mknyga.textbooks.dto.TextbookDTO;
import lt.mknyga.textbooks.dto.TopicListDTO;
import lt.mknyga.textbooks.model.Section;
import lt.mknyga.textbooks.model.Textbook;
import lt.mknyga.textbooks.model.Topic;
import lt.mknyga.textbooks.repository.SectionRepository;
import lt.mknyga.textbooks.repository.TextbookRepository;
import lt.mknyga.textbooks.repository.TopicRepository;
import lt.mknyga.textbooks.service.TextbookService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TextbookServiceImpl implements TextbookService {
    private final TextbookRepository textbookRepository;
    private final SectionRepository sectionRepository;
    private final TopicRepository topicRepository;

    public TextbookServiceImpl(TextbookRepository textbookRepository,
                               SectionRepository sectionRepository, TopicRepository topicRepository) {
        this.textbookRepository = textbookRepository;
        this.sectionRepository = sectionRepository;
        this.topicRepository = topicRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TextbookDTO> findByGradeAndSubject(Integer grade, String subject) {
        return textbookRepository.findByGradeAndSubject(grade, subject)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TextbookDTO> findByGrade(Integer grade) {
        return textbookRepository.findByGrade(grade)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TextbookDTO> findBySubject(String subject) {
        return textbookRepository.findBySubject(subject)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TextbookDTO> findBySlug(String slug) {
        return textbookRepository.findBySlug(slug)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TextbookDTO findByTextbookId(Integer textbookId, boolean includeTopics) {
        //logger.debug("Finding textbook with id: {}", id);

        Textbook textbook = textbookRepository.findByTextbookId(textbookId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Textbook not found"));

        TextbookDTO detailDTO = convertToDTO(textbook);


        if (includeTopics) {
            List<Section> sections = sectionRepository.findByTextbookId(textbookId);
            detailDTO.setSections(sections.stream()
                    .map(this::convertToSectionDTO)
                    .collect(Collectors.toList()));

            List<Topic> topics = topicRepository.findByTextbookId(textbookId);
            detailDTO.setTopics(topics.stream()
                    .map(this::convertToTopicListDTO)
                    .collect(Collectors.toList()));

        }
        return detailDTO;
    }

    @Override
    public TextbookDTO create(Textbook textbook) {
        if (textbookRepository.existsByTextbookId(textbook.getTextbookId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Textbook with this ID already exists");
        }
        Textbook savedTextbook = textbookRepository.save(textbook);
        return convertToDTO(savedTextbook);
    }

    @Override
    public TextbookDTO update(String id, Textbook textbook) {
        if (!textbookRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Textbook not found");
        }
        textbook.setId(id);
        Textbook updatedTextbook = textbookRepository.save(textbook);
        return convertToDTO(updatedTextbook);
    }

    @Override
    public void delete(String id) {
        if (!textbookRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Textbook not found");
        }
        textbookRepository.deleteById(id);
    }

    private TextbookDTO convertToDTO(Textbook textbook) {
        TextbookDTO dto = new TextbookDTO();
        dto.setId(textbook.getId());
        dto.setTextbookId(textbook.getTextbookId());
        dto.setTitle(textbook.getTitle());
        dto.setSlug(textbook.getSlug());
        dto.setGrade(textbook.getGrade());
        dto.setSubject(textbook.getSubject());
        dto.setPublished(textbook.getPublished());
        return dto;
    }

    private SectionDTO convertToSectionDTO(Section section) {
        SectionDTO dto = new SectionDTO();
        dto.setId(section.getId());
        dto.setSectionId(section.getSectionId());
        dto.setTextbookId(section.getTextbookId());
        dto.setBookNo(section.getBookNo());
        dto.setTitle(section.getTitle());
        return dto;
    }

    private TopicListDTO convertToTopicListDTO(Topic topic) {
        TopicListDTO dto = new TopicListDTO();
        dto.setId(topic.getId());
        dto.setTopicId(topic.getTopicId());
        dto.setTextbookId(topic.getTextbookId());
        dto.setSectionId(topic.getSectionId());
        dto.setTitle(topic.getTitle());
        dto.setLessons(topic.getLessons());
        dto.setPages(topic.getPages());
        dto.setPractice(topic.getPractice());
        return dto;
    }
}
