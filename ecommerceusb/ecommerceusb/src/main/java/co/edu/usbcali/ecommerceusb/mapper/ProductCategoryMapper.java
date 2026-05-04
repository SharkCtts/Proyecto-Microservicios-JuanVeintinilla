package co.edu.usbcali.ecommerceusb.mapper;

import co.edu.usbcali.ecommerceusb.dto.ProductCategoryResponse;
import co.edu.usbcali.ecommerceusb.model.ProductCategories;

import java.util.List;

public class ProductCategoryMapper {

    public static ProductCategoryResponse modelToResponse(ProductCategories pc) {
        return ProductCategoryResponse.builder()
                .id(pc.getId())
                .productId(pc.getProduct() != null ? pc.getProduct().getId() : null)
                .categoryId(pc.getCategory() != null ? pc.getCategory().getId() : null)
                .build();
    }

    public static List<ProductCategoryResponse> modelToResponseList(List<ProductCategories> list) {
        return list.stream()
                .map(ProductCategoryMapper::modelToResponse)
                .toList();
    }
}