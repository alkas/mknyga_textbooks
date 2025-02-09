package lt.mknyga.textbooks.service;

import lt.mknyga.textbooks.dto.SectionDTO;
import lt.mknyga.textbooks.model.Section;

import java.util.List;

public interface SectionService {
    List<SectionDTO> findByTextbookId(Integer textbookId);
    SectionDTO findBySectionId(Integer sectionId);
    //SectionDTO create(Section section);
    List<SectionDTO> createBatch(List<Section> sections);
    SectionDTO update(String id, Section section);
    void delete(String id);
}
