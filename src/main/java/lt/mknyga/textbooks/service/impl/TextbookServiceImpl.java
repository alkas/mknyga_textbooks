package lt.mknyga.textbooks.service.impl;

import lt.mknyga.textbooks.dto.SectionDTO;
import lt.mknyga.textbooks.dto.TextbookDTO;
import lt.mknyga.textbooks.dto.TextbookDetailDTO;
import lt.mknyga.textbooks.dto.TextbookListDTO;
import lt.mknyga.textbooks.model.Section;
import lt.mknyga.textbooks.model.Textbook;
import lt.mknyga.textbooks.repository.SectionRepository;
import lt.mknyga.textbooks.repository.TextbookRepository;
import lt.mknyga.textbooks.service.TextbookService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TextbookServiceImpl implements TextbookService {
    private final TextbookRepository textbookRepository;
    private final SectionRepository sectionRepository;

    public TextbookServiceImpl(TextbookRepository textbookRepository,
                               SectionRepository sectionRepository) {
        this.textbookRepository = textbookRepository;
        this.sectionRepository = sectionRepository;
    }

    @Override
    public List<TextbookListDTO> findByGradeAndSubject(Integer grade, String subject) {
        return textbookRepository.findByGradeAndSubject(grade, subject)
                .stream()
                .map(this::convertToListDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TextbookDTO findByTextbookId(Integer textbookId, boolean includeTopics) {
        //logger.debug("Finding textbook with id: {}", id);

        Textbook textbook = textbookRepository.findById(textbookId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Textbook not found"));

        TextbookDetailDTO detailDTO = convertToDetailDTO(textbook);
        if (includeTopics) {
            List<Section> sections = sectionRepository.findByTextbookId(textbook.getTextbookId());
            detailDTO.setSections(sections.stream()
                    .map(this::convertToSectionDTO)
                    .collect(Collectors.toList()));
        }
        return detailDTO;
    }

    @Override
    public TextbookDetailDTO create(Textbook textbook) {
        if (textbookRepository.existsByTextbookId(textbook.getTextbookId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Textbook with this ID already exists");
        }
        Textbook savedTextbook = textbookRepository.save(textbook);
        return convertToDetailDTO(savedTextbook);
    }

    @Override
    public TextbookDetailDTO update(String id, Textbook textbook) {
        if (!textbookRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Textbook not found");
        }
        textbook.setId(id);
        Textbook updatedTextbook = textbookRepository.save(textbook);
        return convertToDetailDTO(updatedTextbook);
    }

    @Override
    public void delete(String id) {
        if (!textbookRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Textbook not found");
        }
        textbookRepository.deleteById(id);
    }

    private TextbookListDTO convertToListDTO(Textbook textbook) {
        TextbookListDTO dto = new TextbookListDTO();
        dto.setId(textbook.getId());
        dto.setTextbookId(textbook.getTextbookId());
        dto.setTitle(textbook.getTitle());
        dto.setSlug(textbook.getSlug());
        dto.setGrade(textbook.getGrade());
        dto.setSubject(textbook.getSubject());
        dto.setPublished(textbook.getPublished());
        return dto;
    }

    private TextbookDetailDTO convertToDetailDTO(Textbook textbook) {
        TextbookDetailDTO dto = new TextbookDetailDTO();
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
        dto.setTitle(section.getTitle());
        return dto;
    }
}
