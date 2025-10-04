package com.example.demo.service;

import com.example.demo.entity.Favorite;
import com.example.demo.repository.FavoriteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {
    private final FavoriteRepository repo;

    public FavoriteService(FavoriteRepository repo) {
        this.repo = repo;
    }

    public Favorite add(Favorite f) {
        return repo.save(f);
    }

    public List<Favorite> findBySlug(String slug) {
        return repo.findBySlug(slug);
    }

    public void remove(Long id) {
        repo.deleteById(id);
    }

    public boolean removeByUserAndSlug(Integer userId, String slug) {
        var fav = repo.findByUserIdAndSlug(userId, slug);
        if (fav.isPresent()) {
            repo.delete(fav.get());
            return true;
        }
        return false;
    }
}
