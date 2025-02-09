package lt.mknyga.textbooks.service;

import lt.mknyga.textbooks.dto.TextbookDTO;
import lt.mknyga.textbooks.dto.TextbookListDTO;
import lt.mknyga.textbooks.model.Textbook;

import java.util.List;

public interface TextbookService {
    List<TextbookListDTO> findByGradeAndSubject(Integer grade, String subject);
    List<TextbookListDTO> findByGrade(Integer grade);
    List<TextbookListDTO> findBySubject(String subject);
    TextbookDTO findByTextbookId(Integer textbookId, boolean includeTopics);
    List<TextbookListDTO> findBySlug(String slug);
    TextbookDTO create(Textbook textbook);
    TextbookDTO update(String id, Textbook textbook);
    void delete(String id);
}
