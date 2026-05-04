package co.edu.usbcali.ecommerceusb.service.impl;

import co.edu.usbcali.ecommerceusb.dto.CategoryResponse;
import co.edu.usbcali.ecommerceusb.dto.CreateCategoryRequest;
import co.edu.usbcali.ecommerceusb.mapper.CategoryMapper;
import co.edu.usbcali.ecommerceusb.model.Categories;
import co.edu.usbcali.ecommerceusb.repository.CategoriesRepository;
import co.edu.usbcali.ecommerceusb.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoriesRepository repository;

    @Override
    public List<CategoryResponse> getAll() {
        List<Categories> list = repository.findAll();

        if (list.isEmpty()) {
            return List.of();
        }

        return CategoryMapper.modelToResponseList(list);
    }

    @Override
    public CategoryResponse getById(Integer id) throws Exception {

        if (id == null) {
            throw new Exception("Debe ingresar el id");
        }

        Categories category = repository.findById(id)
                .orElseThrow(() ->
                        new Exception("Categoría no encontrada con id: " + id)
                );

        return CategoryMapper.modelToResponse(category);
    }

    @Override
    public CategoryResponse create(CreateCategoryRequest request) throws Exception {

        // 🔹 Validaciones
        if (Objects.isNull(request)) {
            throw new Exception("Request null");
        }

        if (request.getName() == null || request.getName().isBlank()) {
            throw new Exception("El nombre es obligatorio");
        }

        if (request.getParentId() == null) {
            throw new Exception("parentId obligatorio");
        }

        // 🔹 Buscar padre
        Categories parent = repository.findById(request.getParentId())
                .orElseThrow(() -> new Exception("Categoría padre no existe"));

        // 🔹 Crear categoría
        Categories category = Categories.builder()
                .name(request.getName())
                .parent(parent)
                .build();

        Categories saved = repository.save(category);

        return CategoryMapper.modelToResponse(saved);
    }
}