package com.julys.eccomerce.eccomerce.dao.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.julys.eccomerce.eccomerce.bd.CategorySql;
import com.julys.eccomerce.eccomerce.entity.Category;

@Component
public class CategoryDAOImpl implements CategoryDAO {

  @Autowired
  private CategorySql categorySql;

  @Override
  public Category createCategory(Category category) {
    try {

      Category category2 = new Category();

      category2.setName(category.getName().toLowerCase());

      return categorySql.save(category2);
    } catch (org.springframework.dao.DataIntegrityViolationException e) {
      System.out.println("Error creating category: " + e.getMessage());
      if (e.getMessage().contains("name")) {
        throw new org.springframework.dao.DataIntegrityViolationException(
            "Error creating category, name is required");
      } else if (e.getMessage().contains("description")) {
        throw new org.springframework.dao.DataIntegrityViolationException(
            "Error creating category, description is required");
      }
      throw new org.springframework.dao.DataIntegrityViolationException(
          "Error creating category: " + e.getMessage());
    } catch (org.springframework.dao.DataAccessResourceFailureException e) {
      throw new org.springframework.dao.DataAccessResourceFailureException(
          "Error creating category: " + e.getMessage());
    } catch (org.springframework.dao.InvalidDataAccessApiUsageException e) {
      throw new org.springframework.dao.InvalidDataAccessApiUsageException(
          "Error creating category: " + e.getMessage());
    } catch (NullPointerException e) {
      throw new NullPointerException("Error creating category: " + e.getMessage());
    }

    catch (Exception e) {

      throw new NullPointerException("Error creating category: " + e.getMessage());
    }
  }

  @Override
  public List<Category> getCategories() {
    try {
      return categorySql.findAll();
    } catch (Exception e) {
      throw new NullPointerException("Error getting categories: " + e.getMessage());
    }
  }

  @Override
  public Category getCategoryById(Long id) {
    try {
      return categorySql.findById(id).orElse(null);
    } catch (Exception e) {
      throw new NullPointerException("Error getting category: " + e.getMessage());
    }
  }

  @Override
  public String deleteCategoryById(Long id) {
    try {
      categorySql.deleteById(id);
      return "Category deleted";
    } catch (Exception e) {
      throw new NullPointerException("Error deleting category: " + e.getMessage());
    }
  }

  @Override
  public List<Category> getCategoryByName(String name) {
    try {
      return categorySql.findByCategoryByName(name);
    } catch (Exception e) {
      throw new NullPointerException("Error getting category: " + e.getMessage());
    }
  }

}