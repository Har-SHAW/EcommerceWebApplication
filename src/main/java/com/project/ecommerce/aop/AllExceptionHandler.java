package com.project.ecommerce.aop;

import com.project.ecommerce.jsp_pages.JspPages;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AllExceptionHandler {
    @ExceptionHandler(Exception.class)
    public String getErrorPage(Exception exception){
        exception.printStackTrace();
        return JspPages.ERROR_PAGE;
    }
}