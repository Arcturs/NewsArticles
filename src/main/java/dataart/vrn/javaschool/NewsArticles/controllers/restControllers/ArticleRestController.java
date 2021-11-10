package dataart.vrn.javaschool.NewsArticles.controllers.restControllers;

import dataart.vrn.javaschool.NewsArticles.entities.tags.Tag;
import dataart.vrn.javaschool.NewsArticles.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
public class ArticleRestController {
    private final ArticleService articleService;
    //@Autowired
    //private ApplicationContext context;
    //private Tag mainTag;

    /*@GetMapping("/article/{id}")
    public ResponseEntity<?> showArticleRest(@PathVariable(value = "id") long id) {
        return ResponseEntity.ok(articleService.findArticleById(id));
    }

    @GetMapping("/")
    public ResponseEntity<?> main(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page) {
        return ResponseEntity.ok(articleService.fetchAllArticles(PageRequest
                .of(page, 2, Sort.by(Sort.Direction.DESC, "id"))));
    }

    @PostMapping("/")
    public ResponseEntity<?> filterArticles(@RequestParam(value = "tag") Tag tag) {
        mainTag = tag;
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(articleService.findArticleByTag(tag));
    }

    @GetMapping("/filter")
    public ResponseEntity<?> filterArticle(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page) {
        return ResponseEntity.ok(articleService.findArticleByTag(mainTag, PageRequest
                .of(page, 2, Sort.by(Sort.Direction.DESC, "id"))));
    }

    @PostMapping("/article-add")
    public ResponseEntity<?> articlePostAdd(@RequestParam("file") MultipartFile file, @RequestParam("tag") Tag tag) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(articleService.getArticleFromFile(file, tag));
    }

    @RequestMapping("/shutdown")
    public void shutdown() {
        shutDownApp(0);
    }

    private void shutDownApp(int returnCode) {
        SpringApplication.exit(context, () -> returnCode);
    }*/
}
