package lt.mknyga.textbooks.repository;

import lt.mknyga.textbooks.model.Topic;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TopicRepository  extends MongoRepository<Topic, String> {
    List<Topic> findByTextbookId(Integer textbookId);
    List<Topic> findByTextbookIdAndSectionId(Integer textbookId, Integer sectionId);
    List<Topic> findByTextbookIdOrderByOrderNo(Integer textbookId);

    @Query("{ 'achievements': { $in: ?0 } }")
    List<Topic> findByAchievementsIn(List<String> achievements);

    @Query("{ 'competencies': { $in: ?0 } }")
    List<Topic> findByCompetenciesIn(List<String> competencies);

    List<Topic> findBySectionId(Integer sectionId);
    Optional<Topic> findById(String id);
}
