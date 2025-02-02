package lt.mknyga.textbooks.service;

import lt.mknyga.textbooks.dto.TextbookDTO;
import lt.mknyga.textbooks.dto.TextbookListDTO;
import lt.mknyga.textbooks.dto.TextbookDetailDTO;
import lt.mknyga.textbooks.model.Textbook;

import java.util.List;

public interface TextbookService {
    List<TextbookDTO> findByGradeAndSubject(Integer grade, String subject);
    TextbookDTO findByTextbookId(Integer textbookId, boolean includeTopics);
    TextbookDTO create(Textbook textbook);
    TextbookDTO update(String id, Textbook textbook);
    void delete(String id);
}
