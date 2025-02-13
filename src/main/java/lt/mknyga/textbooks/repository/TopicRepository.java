package lt.mknyga.textbooks.repository;

import lt.mknyga.textbooks.model.Topic;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TopicRepository  extends MongoRepository<Topic, String> {
    List<Topic> findAllBySectionId(Integer sectionId);
    List<Topic> findAllByTextbookId(Integer textbookId);
    Optional<Topic> findByTopicId(Integer topicId);
    boolean existsByTopicId(Integer topicId);
}
