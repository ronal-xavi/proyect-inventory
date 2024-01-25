package com.ronal.inventory.controller;

import com.ronal.inventory.model.Category;
import com.ronal.inventory.response.CategoryResponseRest;
import com.ronal.inventory.services.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class CategoryRestController {

    private final ICategoryService service;

    /**
     * Get all the categories
     * @return List
     */
    @GetMapping("/categories")
    public ResponseEntity<CategoryResponseRest> searchCategories(){
        return this.service.search();
    }

    /**
     * Get categories by id
     * @param id id
     * @return List
     */
    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> searchCategoriesById(@PathVariable Long id){
        return this.service.searchById(id);
    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryResponseRest> save(@RequestBody Category category) {
        return this.service.save(category);
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> update(@RequestBody Category category, @PathVariable Long id) {
        return this.service.update(id,category);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> delete( @PathVariable Long id) {
        return this.service.deleteById(id);
    }


}
