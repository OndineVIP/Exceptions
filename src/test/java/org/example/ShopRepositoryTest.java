package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ShopRepositoryTest {
    ShopRepository repo = new ShopRepository();
    Product product1 = new Product(1, "кукла", 100);
    Product product2 = new Product(2, "Машинка", 1000);
    Product product3 = new Product(3, "конструктор", 100_000);

    @BeforeEach
    public void setup() {

        repo.add(product1);
        repo.add(product2);
        repo.add(product3);
    }

    @Test
    public void testRemoveExistingProduct() {
        int idToRemove = 2;

        repo.removeById(idToRemove);
        Product[] products = repo.findAll();

        Assertions.assertEquals(2, products.length);
        Product[] expected = {product1, product3 };
        Assertions.assertArrayEquals(expected, products);
    }

    @Test
    public void testRemoveNonExistingProduct() {
        int nonExistingProductId = 10;

        Assertions.assertThrows(NotFoundException.class, () -> repo.removeById(nonExistingProductId));
    }

    @Test
    public void shouldAddProductTest() {
        Product product4 = new Product(4, "мишка", 400);
        repo.add(product4);

        Product[] products = repo.findAll();
        Assertions.assertEquals(4, products.length);
        Product[] expectedProducts = { product1, product2,product3, product4};
        Assertions.assertArrayEquals(expectedProducts, products);
    }

    @Test
    public void shouldNotAddAlreadyExistsProductTest() {
        Product duplicateProduct = new Product(1, "игрушка с  ID", 100);
        Assertions.assertThrows(AlreadyExistsException.class, () -> repo.add(duplicateProduct));
    }
}