package lt.mknyga.textbooks.service.impl;

import lt.mknyga.textbooks.dto.SectionDTO;
import lt.mknyga.textbooks.model.Section;
import lt.mknyga.textbooks.repository.SectionRepository;
import lt.mknyga.textbooks.repository.TextbookRepository;
import lt.mknyga.textbooks.service.SectionService;
import lt.mknyga.textbooks.util.DTOConverter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SectionServiceImpl implements SectionService {
    private final SectionRepository sectionRepository;
    private final TextbookRepository textbookRepository;
    private final DTOConverter dtoConverter;

    public SectionServiceImpl(SectionRepository sectionRepository,
                              TextbookRepository textbookRepository,
                              DTOConverter dtoConverter) {
        this.sectionRepository = sectionRepository;
        this.textbookRepository = textbookRepository;
        this.dtoConverter = dtoConverter;
    }

    @Override
    public List<SectionDTO> findByTextbookId(Integer textbookId) {
        if (!textbookRepository.existsByTextbookId(textbookId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Textbook not found");
        }
        return sectionRepository.findAllByTextbookId(textbookId)
                .stream()
                .map(dtoConverter::convertToSectionDTO)
                .collect(Collectors.toList());
    }

    public SectionDTO findBySectionId(Integer sectionId) {
        Section section = sectionRepository.findBySectionId(sectionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Section not found"));
        return dtoConverter.convertToSectionDTO(section);
    }

    @Override
    public List<SectionDTO> createBatch(List<Section> sections) {
        // Validate that all materials have valid topic IDs
        Set<Integer> textbookIds = sections.stream()
                .map(Section::getTextbookId)
                .collect(Collectors.toSet());

        // Check if all topics exist
        for (Integer textbookId : textbookIds) {
            if (!textbookRepository.existsByTextbookId(textbookId)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Textbook with id %s not found", textbookId));
            }
        }

        // Save all materials
        List<Section> savedSections = sectionRepository.saveAll(sections);

        // Convert and return DTOs
        return savedSections.stream()
                .map(dtoConverter::convertToSectionDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SectionDTO update(String id, Section updatedSection) {
        // Find the existing section
        Section existingSection = sectionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Section not found"));

        if (updatedSection.getTextbookId() != null) {
            if (!textbookRepository.existsByTextbookId(updatedSection.getTextbookId())) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Textbook not found");
            }
            existingSection.setTextbookId(updatedSection.getTextbookId());
        }
        if (updatedSection.getSectionId() != null) {
            existingSection.setSectionId(updatedSection.getSectionId());
        }
        if (updatedSection.getTitle() != null) {
            existingSection.setTitle(updatedSection.getTitle());
        }
        if (updatedSection.getBookNo() != null) {
            existingSection.setBookNo(updatedSection.getBookNo());
        }

        Section savedSection = sectionRepository.save(existingSection);
        return dtoConverter.convertToSectionDTO(savedSection);
    }

    @Override
    public void delete(String id) {
        if (!sectionRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Section not found");
        }
        sectionRepository.deleteById(id);
    }

}
