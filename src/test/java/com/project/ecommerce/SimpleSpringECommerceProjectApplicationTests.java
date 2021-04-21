package com.project.ecommerce;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.project.ecommerce.repository.RoleRepository;
import static org.assertj.core.api.Assertions.assertThat;

import com.project.ecommerce.service.AdminService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
class SimpleSpringECommerceProjectApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	RoleRepository roleRepository;

	@Autowired
	AdminService adminService;

	@Test
	void shouldReturnDefaultMessage() throws Exception {
		this.mockMvc.perform(get("/")).andExpect(status().is(302));
	}

	@Test
	void testRoles(){
		Mockito.when(roleRepository.existsById("ROLE_ADMIN")).thenReturn(true);
		boolean role = adminService.isValidRole("ROLE_ADMIN");
		assertThat(role).isTrue();
	}
}
