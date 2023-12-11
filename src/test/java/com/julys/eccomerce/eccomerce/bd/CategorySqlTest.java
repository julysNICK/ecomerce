package com.julys.eccomerce.eccomerce.bd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.julys.eccomerce.eccomerce.entity.Category;

@SpringBootTest
public class CategorySqlTest {
  @MockBean
  private CategorySql categorySql;

  @Test
  public void testCreateCategory() {
    Category category = new Category();

    category.setName("Test");

    categorySql.save(category);

    assertEquals(category.getName(), "Test");

  }

  @Test
  public void testGetAllCategories() {

    List<Category> categories = new ArrayList<Category>();

    Category category = new Category();

    category.setName("Test");

    categories.add(category);

    Mockito.when(categorySql.findAll()).thenReturn(categories);

    assertEquals(categories.size(), 1);

  }

  @Test
  public void testGetCategoryById() {

    Category category = new Category();

    category.setName("Test");

    Mockito.when(categorySql.findById(1L)).thenReturn(java.util.Optional.of(category));

    assertEquals(category.getName(), "Test");

  }

  @Test
  public void testDeleteCategoryById() {

    Category category = new Category();

    category.setName("Test");

    Mockito.when(categorySql.findById(1L)).thenReturn(java.util.Optional.of(category));

    categorySql.deleteById(1L);

    assertEquals(category.getName(), "Test");

  }
}