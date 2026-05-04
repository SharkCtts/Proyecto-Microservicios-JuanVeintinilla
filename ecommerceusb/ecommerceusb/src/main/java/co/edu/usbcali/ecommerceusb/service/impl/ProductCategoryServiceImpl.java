package co.edu.usbcali.ecommerceusb.service.impl;

import co.edu.usbcali.ecommerceusb.dto.CreateProductCategoryRequest;
import co.edu.usbcali.ecommerceusb.dto.ProductCategoryResponse;
import co.edu.usbcali.ecommerceusb.mapper.ProductCategoryMapper;
import co.edu.usbcali.ecommerceusb.model.Categories;
import co.edu.usbcali.ecommerceusb.model.ProductCategories;
import co.edu.usbcali.ecommerceusb.model.Products;
import co.edu.usbcali.ecommerceusb.repository.CategoriesRepository;
import co.edu.usbcali.ecommerceusb.repository.ProductCategoriesRepository;
import co.edu.usbcali.ecommerceusb.repository.ProductsRepository;
import co.edu.usbcali.ecommerceusb.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoriesRepository repository;

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Override
    public List<ProductCategoryResponse> getAll() {
        List<ProductCategories> list = repository.findAll();

        if (list.isEmpty()) {
            return List.of();
        }

        return ProductCategoryMapper.modelToResponseList(list);
    }

    @Override
    public ProductCategoryResponse getById(Integer id) throws Exception {

        if (id == null) {
            throw new Exception("Debe ingresar el id");
        }

        ProductCategories pc = repository.findById(id)
                .orElseThrow(() ->
                        new Exception("Relación no encontrada con id: " + id)
                );

        return ProductCategoryMapper.modelToResponse(pc);
    }

    @Override
    public ProductCategoryResponse create(CreateProductCategoryRequest request) throws Exception {

        // Validaciones
        if (Objects.isNull(request)) {
            throw new Exception("El request no puede ser null");
        }

        if (request.getProductId() == null || request.getProductId() <= 0) {
            throw new Exception("productId inválido");
        }

        if (request.getCategoryId() == null || request.getCategoryId() <= 0) {
            throw new Exception("categoryId inválido");
        }

        // Buscar relaciones
        Products product = productsRepository.findById(request.getProductId())
                .orElseThrow(() -> new Exception("Producto no existe"));

        Categories category = categoriesRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new Exception("Categoría no existe"));

        // Crear relación
        ProductCategories pc = ProductCategories.builder()
                .product(product)
                .category(category)
                .build();

        ProductCategories saved = repository.save(pc);

        return ProductCategoryMapper.modelToResponse(saved);
    }
}