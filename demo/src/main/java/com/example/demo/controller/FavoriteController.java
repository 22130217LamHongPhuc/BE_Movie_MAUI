package com.example.demo.controller;

import com.example.demo.dto.FavoriteRecordRequest;
import com.example.demo.entity.Favorite;
import com.example.demo.service.FavoriteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {
    private final FavoriteService service;

    public FavoriteController(FavoriteService service) {
        this.service = service;
    }

    @GetMapping
    public List<FavoriteRecordRequest> listByUser(@RequestParam String slug) {

        return service.findBySlug(slug).stream().map(
                favorite -> new FavoriteRecordRequest(
                        Math.toIntExact(favorite.getId())
                        ,favorite.getUserId(),
                        favorite.getSlug())).toList();
    }

    @PostMapping
    public Favorite add(@RequestBody FavoriteRecordRequest record) {
        Favorite favorite = new Favorite();
        favorite.setSlug(record.getSlug());
        favorite.setUserId(record.getUserId());
        return service.add(favorite);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteByUserAndSlug(
            @RequestParam Integer userId,
            @RequestParam String slug) {
        boolean removed = service.removeByUserAndSlug(userId, slug);
        if (removed) {
            return ResponseEntity.noContent().build(); // HTTP 204
        } else {
            return ResponseEntity.notFound().build();  // HTTP 404 nếu không tìm thấy
        }
    }
}
