package co.edu.usbcali.ecommerceusb.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(
        name = "payments",
        indexes = {
                @Index(name = "idx_payments_order", columnList = "order_id")
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_payments_idempotency_key", columnNames = "idempotency_key")
        }
)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 🔹 FK → orders (NOT NULL, ON DELETE RESTRICT)
    @ManyToOne(optional = false)
    @JoinColumn(
            name = "order_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_payments_order")
    )
    private Orders order;

    // 🔹 Enum para respetar CHECK
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;

    @Column(name = "provider_ref")
    private String providerRef;

    @Column(name = "idempotency_key", nullable = false)
    private String idempotencyKey;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    // 🔹 Manejo automático de created_at
    @PrePersist
    protected void onCreate() {
        this.createdAt = OffsetDateTime.now();
    }
}