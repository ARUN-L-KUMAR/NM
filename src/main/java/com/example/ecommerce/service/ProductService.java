package com.example.ecommerce.service;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {
    
    private final ProductRepository productRepository;
    
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    // Create a new product
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
    
    // Get all products
    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    // Get product by ID
    @Transactional(readOnly = true)
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
    
    // Update product
    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setStockQuantity(productDetails.getStockQuantity());
        product.setCategory(productDetails.getCategory());
        product.setBrand(productDetails.getBrand());
        product.setIsActive(productDetails.getIsActive());
        
        return productRepository.save(product);
    }
    
    // Delete product
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        productRepository.delete(product);
    }
    
    // Search products by name
    @Transactional(readOnly = true)
    public List<Product> searchProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }
    
    // Get products by category
    @Transactional(readOnly = true)
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryIgnoreCase(category);
    }
    
    // Get products by brand
    @Transactional(readOnly = true)
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrandIgnoreCase(brand);
    }
    
    // Get active products
    @Transactional(readOnly = true)
    public List<Product> getActiveProducts() {
        return productRepository.findByIsActiveTrue();
    }
    
    // Get inactive products
    @Transactional(readOnly = true)
    public List<Product> getInactiveProducts() {
        return productRepository.findByIsActiveFalse();
    }
    
    // Get products in stock
    @Transactional(readOnly = true)
    public List<Product> getProductsInStock() {
        return productRepository.findProductsInStock();
    }
    
    // Get products out of stock
    @Transactional(readOnly = true)
    public List<Product> getProductsOutOfStock() {
        return productRepository.findProductsOutOfStock();
    }
    
    // Get products by price range
    @Transactional(readOnly = true)
    public List<Product> getProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }
    
    // Search products
    @Transactional(readOnly = true)
    public List<Product> searchProducts(String keyword) {
        return productRepository.searchProducts(keyword);
    }
    
    // Note: Top selling products functionality removed due to simplified order structure
    
    // Get distinct categories
    @Transactional(readOnly = true)
    public List<String> getDistinctCategories() {
        return productRepository.findDistinctCategories();
    }
    
    // Get distinct brands
    @Transactional(readOnly = true)
    public List<String> getDistinctBrands() {
        return productRepository.findDistinctBrands();
    }
    
    // Update stock quantity
    public Product updateStock(Long id, Integer newStockQuantity) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        
        product.setStockQuantity(newStockQuantity);
        return productRepository.save(product);
    }
    

    
    // Activate product
    public Product activateProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        
        product.setIsActive(true);
        return productRepository.save(product);
    }
    
    // Deactivate product
    public Product deactivateProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        
        product.setIsActive(false);
        return productRepository.save(product);
    }
}
