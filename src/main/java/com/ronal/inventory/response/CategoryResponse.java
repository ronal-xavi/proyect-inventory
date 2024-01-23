package com.ronal.inventory.response;

import com.ronal.inventory.model.Category;
import lombok.Data;

import java.util.List;

@Data
public class CategoryResponse {

    private List<Category> categoryList;
}
