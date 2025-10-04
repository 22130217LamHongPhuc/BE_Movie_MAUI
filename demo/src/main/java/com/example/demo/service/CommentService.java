package com.example.demo.service;
import com.example.demo.dto.CreateCommentRequest;
import com.example.demo.entity.Comment;
import com.example.demo.repository.CommentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class CommentService {
    private final CommentRepository repo;
    public CommentService(CommentRepository repo) { this.repo = repo; }

    public Long create(CreateCommentRequest req) {
        Comment c = new Comment();
        c.setUserId(req.getUserId());
        c.setSlug(req.getSlug());
        c.setDisplayName(req.getDisplayName());
        c.setContent(req.getContent());
         c.setCreatedAt(Instant.now());
        return repo.save(c).getId();
    }

    public ResponseEntity<List<Comment>> findAll() {
        return ResponseEntity.ok(repo.findAll());
    }

    public List<Comment> getCommentBySlug(String slug) {
       return repo.findBySlugOrderByCreatedAtDesc(slug);
    }
}
