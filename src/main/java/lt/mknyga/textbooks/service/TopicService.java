package lt.mknyga.textbooks.service;

import lt.mknyga.textbooks.dto.TopicDetailDTO;
import lt.mknyga.textbooks.dto.TopicListDTO;
import lt.mknyga.textbooks.model.Topic;

import java.util.List;

public interface TopicService {
    List<TopicListDTO> findBySectionId(Integer sectionId);
    TopicDetailDTO findById(String id);
    TopicDetailDTO create(Topic topic);
    TopicDetailDTO update(String id, Topic topic);
    void delete(String id);
}
