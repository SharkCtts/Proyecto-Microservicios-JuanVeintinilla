package co.edu.usbcali.ecommerceusb.mapper;

import co.edu.usbcali.ecommerceusb.dto.CategoryResponse;
import co.edu.usbcali.ecommerceusb.model.Categories;

import java.util.List;

public class CategoryMapper {

    public static CategoryResponse modelToResponse(Categories category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .parentId(category.getParent() != null ? category.getParent().getId() : null)
                .createdAt(category.getCreatedAt())
                .build();
    }

    public static List<CategoryResponse> modelToResponseList(List<Categories> list) {
        return list.stream()
                .map(CategoryMapper::modelToResponse)
                .toList();
    }
}