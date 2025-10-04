package com.example.demo.controller;

import com.example.demo.dto.CreateCommentRequest;
import com.example.demo.entity.Comment;
import com.example.demo.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/comments")
class CommentController {
    private final CommentService service;
    public CommentController(CommentService service) { this.service = service; }


    @GetMapping()
    public List<Comment> getBySlug(@RequestParam String slug) {
        return service.getCommentBySlug(slug);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CreateCommentRequest req) {
        Long id = service.create(req); // trả về id sau khi lưu
        return ResponseEntity.ok(Map.of("id", id, "status", "ok"));
    }
}
