package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    ProductServiceImpl productService;

    @Mock
    ProductRepository productRepository;

    @Test
    void testCreateProduct() {
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        when(productRepository.create(product)).thenReturn(product);
        Product result = productService.create(product);

        verify(productRepository, times(1)).create(product);
        assertEquals(product, result);
    }

    @Test
    void testFindAllProducts() {
        Product product1 = new Product();
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);

        Product product2 = new Product();
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);

        Iterator<Product> iterator = List.of(product1, product2).iterator();
        when(productRepository.findAll()).thenReturn(iterator);

        List<Product> result = productService.findAll();

        verify(productRepository, times(1)).findAll();
        assertEquals(2, result.size());
    }

    @Test
    void testFindById() {
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        when(productRepository.findById("1")).thenReturn(product);
        Product result = productService.findById("1");

        verify(productRepository, times(1)).findById("1");
        assertEquals(product, result);
    }

    @Test
    void testFindByIdNotFound() {
        when(productRepository.findById("non-existent-id")).thenReturn(null);
        Product result = productService.findById("non-existent-id");

        verify(productRepository, times(1)).findById("non-existent-id");
        assertNull(result);
    }

    @Test
    void testUpdateProduct() {
        Product product = new Product();
        product.setProductName("Sampo Cap Usep");
        product.setProductQuantity(200);

        when(productRepository.update("1", product)).thenReturn(product);
        Product result = productService.update("1", product);

        verify(productRepository, times(1)).update("1", product);
        assertEquals(product, result);
    }

    @Test
    void testUpdateNonExistentProduct() {
        Product product = new Product();
        product.setProductName("Sampo Cap Usep");
        product.setProductQuantity(200);

        when(productRepository.update("non-existent-id", product)).thenReturn(null);
        Product result = productService.update("non-existent-id", product);

        verify(productRepository, times(1)).update("non-existent-id", product);
        assertNull(result);
    }

    @Test
    void testDeleteProduct() {
        doNothing().when(productRepository).delete("1");
        productService.delete("1");

        verify(productRepository, times(1)).delete("1");
    }
}
