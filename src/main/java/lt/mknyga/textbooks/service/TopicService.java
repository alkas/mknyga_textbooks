package lt.mknyga.textbooks.service;

import lt.mknyga.textbooks.dto.TopicDTO;
import lt.mknyga.textbooks.dto.TopicListDTO;
import lt.mknyga.textbooks.model.Topic;

import java.util.List;

public interface TopicService {
    List<TopicListDTO> findBySectionId(Integer sectionId);
    List<TopicListDTO> findByTextbookId(Integer textbookId);
    TopicDTO findByTopicId(Integer topicId);
    TopicDTO create(Topic topic);
    TopicDTO update(String id, Topic topic);
    void delete(String id);
}
