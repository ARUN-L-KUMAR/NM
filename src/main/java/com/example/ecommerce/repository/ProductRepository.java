package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    // Find products by name (case insensitive)
    List<Product> findByNameContainingIgnoreCase(String name);
    
    // Find products by category
    List<Product> findByCategoryIgnoreCase(String category);
    
    // Find products by brand
    List<Product> findByBrandIgnoreCase(String brand);
    
    // Find active products
    List<Product> findByIsActiveTrue();
    
    // Find inactive products
    List<Product> findByIsActiveFalse();
    
    // Find products in stock
    @Query("SELECT p FROM Product p WHERE p.stockQuantity > 0")
    List<Product> findProductsInStock();
    
    // Find products out of stock
    @Query("SELECT p FROM Product p WHERE p.stockQuantity = 0")
    List<Product> findProductsOutOfStock();
    
    // Find products by price range
    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
    
    // Find products by price less than or equal to
    List<Product> findByPriceLessThanEqual(BigDecimal maxPrice);
    
    // Find products by price greater than or equal to
    List<Product> findByPriceGreaterThanEqual(BigDecimal minPrice);
    
    // Find products by category and active status
    List<Product> findByCategoryIgnoreCaseAndIsActiveTrue(String category);
    
    // Find products by brand and active status
    List<Product> findByBrandIgnoreCaseAndIsActiveTrue(String brand);
    
    // Search products by name, description, category, or brand
    @Query("SELECT p FROM Product p WHERE " +
           "LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(p.category) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(p.brand) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Product> searchProducts(@Param("keyword") String keyword);
    
    // Note: Top selling products functionality removed due to simplified order structure
    
    // Get distinct categories
    @Query("SELECT DISTINCT p.category FROM Product p WHERE p.category IS NOT NULL ORDER BY p.category")
    List<String> findDistinctCategories();
    
    // Get distinct brands
    @Query("SELECT DISTINCT p.brand FROM Product p WHERE p.brand IS NOT NULL ORDER BY p.brand")
    List<String> findDistinctBrands();
}
