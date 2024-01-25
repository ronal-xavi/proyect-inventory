package com.ronal.inventory.services;

import com.ronal.inventory.model.Category;
import com.ronal.inventory.response.CategoryResponseRest;
import org.springframework.http.ResponseEntity;

public interface ICategoryService {
    ResponseEntity<CategoryResponseRest> search();
    ResponseEntity<CategoryResponseRest> searchById(Long id);
    ResponseEntity<CategoryResponseRest> save(Category category);
    ResponseEntity<CategoryResponseRest> update(Long id, Category category);
    ResponseEntity<CategoryResponseRest> deleteById(Long id);
}
