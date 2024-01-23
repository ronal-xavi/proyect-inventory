package com.ronal.inventory.services;

import com.ronal.inventory.dao.ICategoryDao;
import com.ronal.inventory.model.Category;
import com.ronal.inventory.response.CategoryResponseRest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements ICategoryService{

    private final ICategoryDao categoryDao;
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseRest> search() {

        CategoryResponseRest responseRest = new CategoryResponseRest();

        try {
            List<Category> categoryList = categoryDao.findAll();
            responseRest.getCategoryResponse().setCategoryList(categoryList);
            responseRest.setMetadata("Respuesta OK","00", "Respuesta exitosa");

        }catch (Exception e){
            responseRest.setMetadata("Respuesta KO", "-1", "Error al consultar");
            e.getStackTrace();
            return new ResponseEntity<>(responseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(responseRest, HttpStatus.OK);
    }
}
