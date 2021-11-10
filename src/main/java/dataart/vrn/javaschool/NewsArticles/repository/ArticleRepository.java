package dataart.vrn.javaschool.NewsArticles.repository;

import dataart.vrn.javaschool.NewsArticles.entities.Articles;
import dataart.vrn.javaschool.NewsArticles.entities.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends CrudRepository<Articles, Long> {
    Optional<Articles> findByTag(Tag tag);
    Page<Articles> findAll(Pageable pageable);
    Page<Articles> findByTag(Tag tag, Pageable pageable);
}
