package lt.mknyga.textbooks.service;

import lt.mknyga.textbooks.dto.TopicDTO;
import lt.mknyga.textbooks.model.Topic;

import java.util.List;

public interface TopicService {
    List<TopicDTO> findBySectionId(Integer sectionId);
    List<TopicDTO> findByTextbookId(Integer textbookId);
    TopicDTO create(Topic topic);
    TopicDTO update(String id, Topic topic);
    void delete(String id);
}
