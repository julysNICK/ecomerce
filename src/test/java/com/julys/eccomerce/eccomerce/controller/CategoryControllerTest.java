package com.julys.eccomerce.eccomerce.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.julys.eccomerce.eccomerce.config.SecurityConfiguration;
import com.julys.eccomerce.eccomerce.entity.Category;
import com.julys.eccomerce.eccomerce.rest.CategoryController;
import com.julys.eccomerce.eccomerce.service.JwtService;
import com.julys.eccomerce.eccomerce.service.category.CategoryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(CategoryController.class)
@Import(SecurityConfiguration.class)
public class CategoryControllerTest {

  @MockBean
  private CategoryService categoryService;

  @MockBean
  private JwtService jwtService;

  @MockBean
  private AuthenticationProvider authenticationProvider;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void test_requisition_without_jwt() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/api/category")).andExpect(
        MockMvcResultMatchers.status().isForbidden());
  }

  @WithMockUser
  @Test
  public void test_create_category() throws Exception {

    Category category = createCategory("Teste");

    Mockito.doReturn(ResponseEntity.ok(category)).when(categoryService).createCategory(Mockito.any(Category.class));

    mockMvc.perform(MockMvcRequestBuilders.post("/api/category/")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(category)))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @WithMockUser
  @Test
  public void test_get_category_id() throws Exception {

    Category category = createCategory("Teste");

    Mockito.doReturn(ResponseEntity.ok(category)).when(categoryService).getCategoryById(Mockito.anyLong());

    mockMvc.perform(MockMvcRequestBuilders.get("/api/category/1")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(category)))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @WithMockUser
  @Test
  public void test_get_category_not_found_status() throws Exception {

    Mockito.doReturn(ResponseEntity.badRequest().build()).when(categoryService)
        .getCategoryById(Mockito.anyLong());

    mockMvc.perform(MockMvcRequestBuilders.get("/api/category/1")
        .contentType("application/json"))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
  }

  @WithMockUser
  @Test
  public void test_get_category_not_found_body() throws Exception {

    Mockito.doReturn(ResponseEntity.badRequest().body("Category not found")).when(categoryService)
        .getCategoryById(Mockito.anyLong());

    mockMvc.perform(MockMvcRequestBuilders.get("/api/category/1")
        .contentType("application/json"))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.content().string("Category not found"));
  }

  @WithMockUser
  @Test
  public void test_get_all_categories() throws Exception {

    Category category = createCategory("Teste");

    Mockito.doReturn(ResponseEntity.ok(category)).when(categoryService).getCategories();

    mockMvc.perform(MockMvcRequestBuilders.get("/api/category/")
        .contentType("application/json"))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  private Category createCategory(String name) {
    Category category = new Category();
    category.setName(name);
    return category;

  }
}