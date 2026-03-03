package co.edu.usbcali.ecommerceusb.repository;

import co.edu.usbcali.ecommerceusb.model.CartItems;
import co.edu.usbcali.ecommerceusb.model.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CartItemsRepository extends JpaRepository<CartItems, Integer> {
}
