package com.ronal.inventory.services;

import com.ronal.inventory.dao.ICategoryDao;
import com.ronal.inventory.model.Category;
import com.ronal.inventory.response.CategoryResponseRest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:4200"})
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements ICategoryService {

    private final ICategoryDao categoryDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseRest> search() {

        CategoryResponseRest responseRest = new CategoryResponseRest();

        try {
            List<Category> categoryList = categoryDao.findAll();
            responseRest.getCategoryResponse().setCategoryList(categoryList);
            responseRest.setMetadata("Respuesta OK", "00", "Respuesta exitosa");

        } catch (Exception e) {
            responseRest.setMetadata("Respuesta KO", "-1", "Error al consultar");
            e.getStackTrace();
            return new ResponseEntity<>(responseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(responseRest, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseRest> searchById(Long id) {

        CategoryResponseRest responseRest = new CategoryResponseRest();
        List<Category> list = new ArrayList<>();
        try {
            Optional<Category> category = this.categoryDao.findById(id);

            if (category.isPresent()) {
                list.add(category.get());
                responseRest.getCategoryResponse().setCategoryList(list);
                responseRest.setMetadata("Respuesta OK", "00", "Respuesta exitosa");

            } else {
                responseRest.setMetadata("Respuesta KO", "-1", "Categoría no encontrada.");
                return new ResponseEntity<>(responseRest, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            responseRest.setMetadata("Respuesta KO", "-1", "Error al consultar por id");
            e.getStackTrace();
            return new ResponseEntity<>(responseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(responseRest, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CategoryResponseRest> save(Category category) {

        CategoryResponseRest responseRest = new CategoryResponseRest();
        List<Category> list = new ArrayList<>();
        try {
            Category categorySave = this.categoryDao.save(category);

            if (categorySave != null) {
                list.add(categorySave);
                responseRest.getCategoryResponse().setCategoryList(list);
                responseRest.setMetadata("Respuesta OK", "00", "Categoría guardada exitosamente");
            } else {
                responseRest.setMetadata("Respuesta KO", "-1", "Categoría no guardada.");
                return new ResponseEntity<>(responseRest, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            responseRest.setMetadata("Respuesta KO", "-1", "Error al grabar categoría");
            e.getStackTrace();
            return new ResponseEntity<>(responseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(responseRest, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoryResponseRest> update(Long id, Category category) {

        CategoryResponseRest responseRest = new CategoryResponseRest();
        List<Category> list = new ArrayList<>();

        try {
            Optional<Category> categorySeach = this.categoryDao.findById(id);

            if (categorySeach.isPresent()) {
                categorySeach.get().setName(category.getName());
                categorySeach.get().setDescription(category.getDescription());

                Category categoryToUpdate = this.categoryDao.save(categorySeach.get());

                if (categoryToUpdate != null) {
                    list.add(categoryToUpdate);
                    responseRest.getCategoryResponse().setCategoryList(list);
                    responseRest.setMetadata("Respuesta OK", "00", "Categoría actualizada exitosamente");
                } else {
                    responseRest.setMetadata("Respuesta OK", "00", "Categoría no exitosamente");
                    return new ResponseEntity<>(responseRest, HttpStatus.BAD_REQUEST);
                }
            } else {
                responseRest.setMetadata("Respuesta KO", "-1", "Categoría no guardada.");
                return new ResponseEntity<>(responseRest, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            responseRest.setMetadata("Respuesta KO", "-1", "Error al actualizar categoría");
            e.getStackTrace();
            return new ResponseEntity<>(responseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(responseRest, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoryResponseRest> deleteById(Long id) {
        CategoryResponseRest responseRest = new CategoryResponseRest();

        try {
            Optional<Category> categorySeach = this.categoryDao.findById(id);
            if (categorySeach.isPresent()) {
                this.categoryDao.deleteById(id);
                responseRest.setMetadata("Respuesta OK", "00", "Registro Eliminado");

            } else {
                responseRest.setMetadata("Respuesta KO", "-1", "Categoría no existe");
                return new ResponseEntity<>(responseRest, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            responseRest.setMetadata("Respuesta KO", "-1", "Error al eliminar categoría");
            e.getStackTrace();
            return new ResponseEntity<>(responseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(responseRest, HttpStatus.OK);

    }
}












































