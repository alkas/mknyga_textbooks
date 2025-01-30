package lt.mknyga.textbooks.service;

import lt.mknyga.textbooks.dto.TextbookListDTO;
import lt.mknyga.textbooks.dto.TextbookDetailDTO;
import lt.mknyga.textbooks.model.Textbook;

import java.util.List;

public interface TextbookService {
    List<TextbookListDTO> findByGradeAndSubject(Integer grade, String subject);
    TextbookDetailDTO findById(String id, boolean includeTopics);
    TextbookDetailDTO create(Textbook textbook);
    TextbookDetailDTO update(String id, Textbook textbook);
    void delete(String id);
}
