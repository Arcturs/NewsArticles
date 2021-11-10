package dataart.vrn.javaschool.NewsArticles.exceptions;

public class ArticleDoesNotExist extends RuntimeException{
    public ArticleDoesNotExist(String message) {
        super(message);
    }
}
