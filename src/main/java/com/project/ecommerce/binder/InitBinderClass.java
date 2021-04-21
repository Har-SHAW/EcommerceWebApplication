package com.project.ecommerce.binder;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

public class InitBinderClass {
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        var stringTrimmer = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmer);
    }
}
