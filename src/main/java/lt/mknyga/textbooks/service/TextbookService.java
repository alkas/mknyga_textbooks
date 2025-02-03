package lt.mknyga.textbooks.service;

import lt.mknyga.textbooks.dto.TextbookDTO;
import lt.mknyga.textbooks.model.Textbook;

import java.util.List;

public interface TextbookService {
    List<TextbookDTO> findByGradeAndSubject(Integer grade, String subject);
    List<TextbookDTO> findByGrade(Integer grade);
    List<TextbookDTO> findBySubject(String subject);
    TextbookDTO findByTextbookId(Integer textbookId, boolean includeTopics);
    List<TextbookDTO> findBySlug(String slug);
    TextbookDTO create(Textbook textbook);
    TextbookDTO update(String id, Textbook textbook);
    void delete(String id);
}
