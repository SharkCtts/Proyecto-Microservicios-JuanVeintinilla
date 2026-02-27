package co.edu.usbcali.ecommerceusb.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "order_items",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_order_product",
                        columnNames = {"order_id", "product_id"}
                )
        },
        indexes = {
                @Index(name = "idx_order_items_order", columnList = "order_id")
        }
)
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 🔹 FK a orders (ON DELETE CASCADE)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "order_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_order_items_order")
    )
    private Orders order;

    // 🔹 FK a products (ON DELETE RESTRICT)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "product_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_order_items_product")
    )
    private Products product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(
            name = "unit_price_snapshot",
            nullable = false,
            precision = 12,
            scale = 2
    )
    private BigDecimal unitPriceSnapshot;

    @Column(
            name = "line_total",
            nullable = false,
            precision = 12,
            scale = 2
    )
    private BigDecimal lineTotal;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    // 🔹 Auto set timestamp
    @PrePersist
    public void prePersist() {
        this.createdAt = OffsetDateTime.now();
        calculateLineTotal();
        validate();
    }

    @PreUpdate
    public void preUpdate() {
        calculateLineTotal();
        validate();
    }

    private void calculateLineTotal() {
        if (unitPriceSnapshot != null && quantity != null) {
            this.lineTotal = unitPriceSnapshot.multiply(BigDecimal.valueOf(quantity));
        }
    }

    private void validate() {
        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que 0");
        }
        if (unitPriceSnapshot == null || unitPriceSnapshot.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El precio unitario no puede ser negativo");
        }
    }
}