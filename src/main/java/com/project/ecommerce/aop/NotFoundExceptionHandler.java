package com.project.ecommerce.aop;

import com.project.ecommerce.exceptions.CartNotInitialisedException;
import com.project.ecommerce.jsp_pages.JspPages;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class NotFoundExceptionHandler {
    @ExceptionHandler(NoHandlerFoundException.class)
    public String requestHandlingNoHandlerFound() {
        return "redirect:/";
    }

    @ExceptionHandler(CartNotInitialisedException.class)
    public String noCartExceptionHandler() {
        return "redirect:/dashboard";
    }

    @ExceptionHandler(Exception.class)
    public String getErrorPage(Exception exception){
        return JspPages.ERROR_PAGE;
    }
}
