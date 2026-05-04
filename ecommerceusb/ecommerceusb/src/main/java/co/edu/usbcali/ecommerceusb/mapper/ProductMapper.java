package co.edu.usbcali.ecommerceusb.mapper;

import co.edu.usbcali.ecommerceusb.dto.CreateProductRequest;
import co.edu.usbcali.ecommerceusb.dto.ProductResponse;
import co.edu.usbcali.ecommerceusb.model.Products;

import java.util.List;

public class ProductMapper {

    public static ProductResponse modelToResponse(Products product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .available(product.getAvailable())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

    public static List<ProductResponse> modelToResponseList(List<Products> products) {
        return products.stream()
                .map(ProductMapper::modelToResponse)
                .toList();
    }

    public static Products requestToModel(CreateProductRequest request) {
        return Products.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .available(request.getAvailable())
                .build();
    }
}