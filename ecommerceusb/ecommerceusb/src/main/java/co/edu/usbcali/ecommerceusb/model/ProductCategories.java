package co.edu.usbcali.ecommerceusb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*; // Importar anotaciones JPA
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_category")

public class ProductCategories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // crea llave primaria tipo serial/autoincremental
    private Integer id;

    @Column(name = "product_id", nullable = false)
    private Integer productId;

    @Column(name = "category_id", nullable = false)
    private Integer categoryId;
}