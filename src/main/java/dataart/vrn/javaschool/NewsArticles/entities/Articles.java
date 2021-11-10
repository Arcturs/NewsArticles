package dataart.vrn.javaschool.NewsArticles.entities;

import dataart.vrn.javaschool.NewsArticles.entities.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Articles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String article;
    @Column(columnDefinition = "ENUM('Sports', 'Finances', 'Travel', 'Politics', 'Culture')")
    private Tag tag;

    public Articles(String title, String article) {
        this.title = title;
        this.article = article;
    }

    public Articles(String title, String article, Tag tag) {
        this.title = title;
        this.article = article;
        this.tag = tag;
    }
}
