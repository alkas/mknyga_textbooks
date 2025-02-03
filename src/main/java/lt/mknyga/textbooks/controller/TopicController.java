package lt.mknyga.textbooks.controller;

import lt.mknyga.textbooks.dto.TopicDTO;
import lt.mknyga.textbooks.dto.TopicListDTO;
import lt.mknyga.textbooks.model.Topic;
import lt.mknyga.textbooks.service.TopicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TopicController {
    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("/section_topics/{sectionId}")
    public ResponseEntity<List<TopicListDTO>> getTopicsBySection(
            @PathVariable Integer sectionId) {
        return ResponseEntity.ok(topicService.findBySectionId(sectionId));
    }

    @GetMapping("/textbook_topics/{textbookId}")
    public ResponseEntity<List<TopicListDTO>> getTopicsByTextbook(
            @PathVariable Integer textbookId) {
        return ResponseEntity.ok(topicService.findByTextbookId(textbookId));
    }

    @GetMapping("/topic/{topicId}")
    public ResponseEntity<TopicDTO> getTopic(@PathVariable Integer topicId) {
        return ResponseEntity.ok(topicService.findByTopicId(topicId));
    }

    @PostMapping("/topics")
    public ResponseEntity<TopicDTO> createTopic(@RequestBody Topic topic) {
        return new ResponseEntity<>(topicService.create(topic), HttpStatus.CREATED);
    }

    @PutMapping("/topics/{id}")
    public ResponseEntity<TopicDTO> updateTopic(
            @PathVariable String id,
            @RequestBody Topic topic) {
        return ResponseEntity.ok(topicService.update(id, topic));
    }

    @DeleteMapping("/topics/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable String id) {
        topicService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
