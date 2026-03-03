package co.edu.usbcali.ecommerceusb.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(
        name = "inventory_movements",
        indexes = {
                @Index(name = "idx_inv_mov_product_created_at", columnList = "product_id, created_at"),
                @Index(name = "idx_inv_mov_order", columnList = "order_id")
        }
)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryMovements {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")

    private Integer id;

    // 🔹 FK → products (NOT NULL, ON DELETE RESTRICT)
    @ManyToOne(optional = false)
    @JoinColumn(
            name = "product_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_inv_mov_product"),
            referencedColumnName = "id"

            )
    private Products product;

    // 🔹 FK → orders (nullable, ON DELETE SET NULL)
    @ManyToOne
    @JoinColumn(
            name = "order_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_inv_mov_order"),
            referencedColumnName = "id"

    )
    private Orders order;

    // 🔹 Enum para respetar CHECK constraint
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MovementType type;

    // 🔹 qty > 0
    @Column(nullable = false)
    private Integer qty;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    // 🔹 Timestamp automático
    @PrePersist
    protected void onCreate() {
        this.createdAt = OffsetDateTime.now();
    }
}