package dataart.vrn.javaschool.NewsArticles.controllers;

import dataart.vrn.javaschool.NewsArticles.NewsArticlesApplication;
import dataart.vrn.javaschool.NewsArticles.entities.Articles;
import dataart.vrn.javaschool.NewsArticles.entities.tags.Tag;
import dataart.vrn.javaschool.NewsArticles.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    @Autowired
    private ApplicationContext context;
    private Tag mainTag;

    @GetMapping("/")
    public String main(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page, Model model) {
        Page<Articles> p = articleService.fetchAllArticles(PageRequest.of(page, 2, Sort.by(Sort.Direction.DESC, "id")));
        model.addAttribute("articlesPage", p);
        model.addAttribute("pages", IntStream.range(0, p.getTotalPages()).toArray());
        return "main";
    }

    @PostMapping("/")
    public String filterArticleByTag(@RequestParam("tag") Tag tag, Model model) {
        mainTag = tag;
        return "redirect:/filter";
    }

    @GetMapping("/filter")
    public String filterArticle(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page, Model model) {
        Page<Articles> p = articleService.findArticleByTag(mainTag, PageRequest.of(page, 2, Sort.by(Sort.Direction.DESC, "id")));
        model.addAttribute("articlesTagPage", p);
        model.addAttribute("pagesTag", IntStream.range(0, p.getTotalPages()).toArray());
        return "filter";
    }

    @GetMapping("/article/{id}")
    public String showArticle(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("articles", articleService.findArticleById(id));
        return "article";
    }

    @GetMapping("/article-add")
    public String articleAdd(Model model) {
        return "article-add";
    }

    @PostMapping("/article-add")
    public String articlePostAdd(@RequestParam("file") MultipartFile file, @RequestParam("tag") Tag tag,
                                 Model model) {
        if (file.isEmpty()) return "redirect:/article-add";
        model.addAttribute("articles", articleService.getArticleFromFile(file, tag));
        return "redirect:/";
    }

    @RequestMapping("/shutdown")
    public void shutdown() {
        shutDownApp(0);
    }

    private void shutDownApp(int returnCode) {
        SpringApplication.exit(context, () -> returnCode);
    }
}
