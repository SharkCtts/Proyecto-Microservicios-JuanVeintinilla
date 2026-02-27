package co.edu.usbcali.ecommerceusb.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "product_categories",
        indexes = {
                @Index(name = "idx_product_categories_category",
                        columnList = "category_id, product_id")
        }
)
public class ProductCategories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 🔹 FK hacia products
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "product_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_pc_product")
    )
    private Products product;

    // 🔹 FK hacia categories
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "category_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_pc_category")
    )
    private Categories category;
}