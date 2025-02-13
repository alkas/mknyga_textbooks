package lt.mknyga.textbooks.service.impl;

import lt.mknyga.textbooks.dto.TextbookDTO;
import lt.mknyga.textbooks.dto.TextbookListDTO;
import lt.mknyga.textbooks.model.Section;
import lt.mknyga.textbooks.model.Textbook;
import lt.mknyga.textbooks.model.Topic;
import lt.mknyga.textbooks.repository.SectionRepository;
import lt.mknyga.textbooks.repository.TextbookRepository;
import lt.mknyga.textbooks.repository.TopicRepository;
import lt.mknyga.textbooks.service.TextbookService;
import lt.mknyga.textbooks.util.DTOConverter;
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
    private final DTOConverter dtoConverter;

    public TextbookServiceImpl(TextbookRepository textbookRepository,
                               SectionRepository sectionRepository,
                               TopicRepository topicRepository,
                               DTOConverter dtoConverter) {
        this.textbookRepository = textbookRepository;
        this.sectionRepository = sectionRepository;
        this.topicRepository = topicRepository;
        this.dtoConverter = dtoConverter;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TextbookListDTO> findByGradeAndSubject(Integer grade, String subject) {
        return textbookRepository.findAllByGradeAndSubject(grade, subject)
                .stream()
                .map(dtoConverter::convertToTextbookListDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TextbookListDTO> findByGrade(Integer grade) {
        return textbookRepository.findAllByGrade(grade)
                .stream()
                .map(dtoConverter::convertToTextbookListDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TextbookListDTO> findBySubject(String subject) {
        return textbookRepository.findAllBySubject(subject)
                .stream()
                .map(dtoConverter::convertToTextbookListDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TextbookListDTO> findBySlug(String slug) {
        return textbookRepository.findAllBySlug(slug)
                .stream()
                .map(dtoConverter::convertToTextbookListDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TextbookDTO findByTextbookId(Integer textbookId, boolean includeTopics) {
        //logger.debug("Finding textbook with id: {}", id);

        Textbook textbook = textbookRepository.findByTextbookId(textbookId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Textbook not found"));

        if (!includeTopics) {
            return dtoConverter.convertToTextbookDTO(textbook, null, null);
        }

        List<Section> sections = sectionRepository.findAllByTextbookId(textbookId);
        List<Topic> topics = topicRepository.findAllByTextbookId(textbookId);

        return dtoConverter.convertToTextbookDTO(textbook, sections, topics);
    }

    @Override
    public TextbookDTO create(Textbook textbook) {
        if (textbookRepository.existsByTextbookId(textbook.getTextbookId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Textbook with this ID already exists");
        }
        Textbook savedTextbook = textbookRepository.save(textbook);
        return dtoConverter.convertToTextbookDTO(savedTextbook, null, null);
    }

    @Override
    public TextbookDTO update(String id, Textbook updatedTextbook) {
        // Find the existing topic
        Textbook existingTextbook = textbookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Textbook not found"));

        // Update only the non-null fields from the request
        if (updatedTextbook.getTextbookId() != null) {
            existingTextbook.setTextbookId(updatedTextbook.getTextbookId());
        }
        if (updatedTextbook.getTitle() != null) {
            existingTextbook.setTitle(updatedTextbook.getTitle());
        }
        if (updatedTextbook.getSubject() != null) {
            existingTextbook.setSubject(updatedTextbook.getSubject());
        }
        if (updatedTextbook.getSlug() != null) {
            existingTextbook.setSlug(updatedTextbook.getSlug());
        }
        if (updatedTextbook.getGrade() != null) {
            existingTextbook.setGrade(updatedTextbook.getGrade());
        }
        if (updatedTextbook.getPublished() != null) {
            existingTextbook.setPublished(updatedTextbook.getPublished());
        }

        // Save the updated textbook
        Textbook savedTextbook = textbookRepository.save(existingTextbook);
        return dtoConverter.convertToTextbookDTO(savedTextbook, null, null);
    }

    @Override
    public void delete(String id) {
        if (!textbookRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Textbook not found");
        }
        textbookRepository.deleteById(id);
    }
}
