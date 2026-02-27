package co.edu.usbcali.ecommerceusb.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "inventory")
public class Inventory {

    // 🔹 PK compartida con Product
    @Id
    @Column(name = "product_id")
    private Integer productId;

    // 🔹 Relación OneToOne con PK compartida
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(
            name = "product_id",
            foreignKey = @ForeignKey(name = "fk_inventory_product")
    )
    private Products product;

    @Column(nullable = false)
    private Integer stock = 0;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    @PrePersist
    @PreUpdate
    public void updateTimestamp() {
        this.updatedAt = OffsetDateTime.now();
    }

    @PrePersist
    @PreUpdate
    public void validateStock() {
        if (stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
    }
}