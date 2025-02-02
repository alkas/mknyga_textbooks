package lt.mknyga.textbooks.service.impl;

import lt.mknyga.textbooks.dto.SectionDTO;
import lt.mknyga.textbooks.model.Section;
import lt.mknyga.textbooks.repository.SectionRepository;
import lt.mknyga.textbooks.repository.TextbookRepository;
import lt.mknyga.textbooks.service.SectionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SectionServiceImpl implements SectionService {
    private final SectionRepository sectionRepository;
    private final TextbookRepository textbookRepository;

    public SectionServiceImpl(SectionRepository sectionRepository,
                              TextbookRepository textbookRepository) {
        this.sectionRepository = sectionRepository;
        this.textbookRepository = textbookRepository;
    }

    @Override
    public List<SectionDTO> findByTextbookId(Integer textbookId) {
        if (!textbookRepository.existsByTextbookId(textbookId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Textbook not found");
        }
        return sectionRepository.findByTextbookId(textbookId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public SectionDTO findById(String id) {
        Section section = sectionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Section not found"));
        return convertToDTO(section);
    }

    public SectionDTO findBySectionId(Integer sectionId) {
        Section section = sectionRepository.findBySectionId(sectionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Section not found"));
        return convertToDTO(section);
    }

    @Override
    public SectionDTO create(Section section) {
        if (!textbookRepository.existsByTextbookId(section.getTextbookId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Textbook not found");
        }
        if (sectionRepository.existsBySectionId(section.getSectionId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Section with this ID already exists");
        }
        Section savedSection = sectionRepository.save(section);
        return convertToDTO(savedSection);
    }

    @Override
    public SectionDTO update(String id, Section section) {
        if (!sectionRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Section not found");
        }
        if (!textbookRepository.existsByTextbookId(section.getTextbookId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Textbook not found");
        }
        section.setId(id);
        Section updatedSection = sectionRepository.save(section);
        return convertToDTO(updatedSection);
    }

    @Override
    public void delete(String id) {
        if (!sectionRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Section not found");
        }
        sectionRepository.deleteById(id);
    }

    private SectionDTO convertToDTO(Section section) {
        SectionDTO dto = new SectionDTO();
        dto.setId(section.getId());
        dto.setSectionId(section.getSectionId());
        dto.setTextbookId(section.getTextbookId());
        dto.setTitle(section.getTitle());
        return dto;
    }
}
