package lt.mknyga.textbooks.controller;

import lt.mknyga.textbooks.dto.TopicDetailDTO;
import lt.mknyga.textbooks.dto.TopicListDTO;
import lt.mknyga.textbooks.model.Topic;
import lt.mknyga.textbooks.service.TopicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topics")
public class TopicController {
    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("/section/{sectionId}")
    public ResponseEntity<List<TopicListDTO>> getTopicsBySection(
            @PathVariable Integer sectionId) {
        return ResponseEntity.ok(topicService.findBySectionId(sectionId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicDetailDTO> getTopic(@PathVariable String id) {
        return ResponseEntity.ok(topicService.findItemById(id));
    }

    @PostMapping
    public ResponseEntity<TopicDetailDTO> createTopic(@RequestBody Topic topic) {
        return new ResponseEntity<>(topicService.create(topic), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicDetailDTO> updateTopic(
            @PathVariable String id,
            @RequestBody Topic topic) {
        return ResponseEntity.ok(topicService.update(id, topic));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable String id) {
        topicService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
