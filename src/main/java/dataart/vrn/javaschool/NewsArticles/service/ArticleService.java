package dataart.vrn.javaschool.NewsArticles.service;

import dataart.vrn.javaschool.NewsArticles.entities.Articles;
import dataart.vrn.javaschool.NewsArticles.entities.tags.Tag;
import dataart.vrn.javaschool.NewsArticles.exceptions.ArticleDoesNotExist;
import dataart.vrn.javaschool.NewsArticles.exceptions.EmptyFile;
import dataart.vrn.javaschool.NewsArticles.exceptions.WrongFileType;
import dataart.vrn.javaschool.NewsArticles.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
@RequiredArgsConstructor
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public Articles getArticleFromFile(MultipartFile file, Tag tag) {
        try {
            ZipInputStream in = new ZipInputStream(file.getInputStream());
            ZipEntry entry = in.getNextEntry();
            if (entry == null) throw new EmptyFile("Invalid type of file");
            if (!entry.getName().equals("article.txt")) throw new WrongFileType("Invalid file in archive");
            Scanner scanner = new Scanner(in, StandardCharsets.UTF_8);
            if (!scanner.hasNext()) throw new EmptyFile("File is empty");
            String title = scanner.nextLine();
            if (!scanner.hasNext()) throw new WrongFileType("Empty body in article");
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNext()) {
                sb.append(scanner.nextLine()).append("<br/>");
            }
            String text = sb.toString();
            if ((entry = in.getNextEntry()) != null) throw new WrongFileType("There are other files in archive");
            return articleRepository.save(new Articles(title, text, tag));
        } catch (IOException e) {
            throw new WrongFileType("Invalid type of file");
        }
    }

    public Page<Articles> fetchAllArticles(Pageable pageable) {
        return articleRepository.findAll(pageable);
    }

    public Articles findArticleById(long id) {
        Optional<Articles> article = articleRepository.findById(id);
        if (article.isPresent())
            return article.get();
        else throw new ArticleDoesNotExist("Article with following id does not exist");
    }

    public Articles findArticleByTag(Tag tag) {
        Optional<Articles> article = articleRepository.findByTag(tag);
        if (article.isPresent())
            return article.get();
        else throw new ArticleDoesNotExist("Articles with following tag do not exist");
    }

    public Page<Articles> findArticleByTag(Tag tag, Pageable pageable) {
        return articleRepository.findByTag(tag, pageable);
    }
}
