package dataart.vrn.javaschool.NewsArticles.controllers;

import dataart.vrn.javaschool.NewsArticles.exceptions.ArticleDoesNotExist;
import dataart.vrn.javaschool.NewsArticles.exceptions.EmptyFile;
import dataart.vrn.javaschool.NewsArticles.exceptions.WrongFileType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String handleRuntimeException(RuntimeException e) {
        return e.getMessage();
    }

    @ExceptionHandler(ArticleDoesNotExist.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public String handleNotFoundException(ArticleDoesNotExist e) {
        return e.getMessage();
    }

    @ExceptionHandler(WrongFileType.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleWrongFileType(WrongFileType e) {
        return e.getMessage();
    }

    @ExceptionHandler(EmptyFile.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleEmptyFile(EmptyFile e) {
        return e.getMessage();
    }

}
