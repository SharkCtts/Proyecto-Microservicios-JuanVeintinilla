package co.edu.usbcali.ecommerceusb.repository;

import co.edu.usbcali.ecommerceusb.model.Carts;
import co.edu.usbcali.ecommerceusb.model.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CartsRepository extends JpaRepository<Carts, Integer> {
}
