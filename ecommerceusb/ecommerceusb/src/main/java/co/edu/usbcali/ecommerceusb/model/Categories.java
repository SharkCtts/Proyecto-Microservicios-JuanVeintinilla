package co.edu.usbcali.ecommerceusb.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")

    private Integer id;

    @Column(nullable = false)
    private String name;

    // 🔹 Relación jerárquica (self reference)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "parent_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_categories_parent"),
            referencedColumnName = "id"

            )
    private Categories parent;

    // 🔹 Relación inversa (hijos)
    @OneToMany(mappedBy = "parent")
    private Set<Categories> children;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = OffsetDateTime.now();
    }
}




