package com.ronal.inventory.dao;

import com.ronal.inventory.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryDao extends JpaRepository<Category,Long> {
}
