package com.project.ecommerce.controller;

import com.project.ecommerce.dto.user.UserSignup;
import com.project.ecommerce.repository.UserRepository;
import com.project.ecommerce.validation.OnlyGMail;
import com.project.ecommerce.validation.OnlyGMailConstraintValidator;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.validation.ConstraintValidatorContext;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserRepository userRepository;

    @MockBean
    OnlyGMail onlyGMail;

    @MockBean
    ConstraintValidatorContext context;

    @Test
    void testValidation() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/processSignup")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", "")
                .param("password", "")
                .param("confirmPassword", "")
                .param("age", "")
                .param("email", "")
                .param("phoneNo", "")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .sessionAttr("user", new UserSignup())
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("sign-up"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/view/sign-up.jsp"))
                .andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("user", "username"))
                .andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("user", "password"))
                .andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("user", "confirmPassword"))
                .andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("user", "age"))
                .andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("user", "email"))
                .andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("user", "phoneNo"))
                .andExpect(MockMvcResultMatchers.model().attribute("user", Matchers.hasProperty("username", Matchers.nullValue())))
                .andExpect(MockMvcResultMatchers.model().attribute("user", Matchers.hasProperty("password", Matchers.nullValue())))
                .andExpect(MockMvcResultMatchers.model().attribute("user", Matchers.hasProperty("confirmPassword", Matchers.nullValue())))
                .andExpect(MockMvcResultMatchers.model().attribute("user", Matchers.hasProperty("age", Matchers.nullValue())))
                .andExpect(MockMvcResultMatchers.model().attribute("user", Matchers.hasProperty("email", Matchers.nullValue())))
                .andExpect(MockMvcResultMatchers.model().attribute("user", Matchers.hasProperty("phoneNo", Matchers.nullValue())));
    }

    @Test
    void testUniqueUsernameValidation() throws Exception {
        Mockito.when(userRepository.existsById("shaw")).thenReturn(true);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/processSignup")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", "shaw")
                .param("password", "shawshaw")
                .param("confirmPassword", "shawshaw")
                .param("age", "70")
                .param("email", "harsha@gmail.com")
                .param("phoneNo", "90909090")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .sessionAttr("user", new UserSignup())
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("sign-up"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/view/sign-up.jsp"))
                .andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("user", "age"))
                .andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("user", "phoneNo"));
    }

    @Test
    void testOnlyGmail(){
        Mockito.when(onlyGMail.value()).thenReturn("gmail.com");
        OnlyGMailConstraintValidator onlyGMailConstraintValidator = new OnlyGMailConstraintValidator();
        onlyGMailConstraintValidator.initialize(onlyGMail);

        boolean result = onlyGMailConstraintValidator.isValid("shaw@gmail.com", context);
        Assertions.assertThat(result).isTrue();

        result = onlyGMailConstraintValidator.isValid("shaw@yahoo.com", context);
        Assertions.assertThat(result).isFalse();
    }
}
