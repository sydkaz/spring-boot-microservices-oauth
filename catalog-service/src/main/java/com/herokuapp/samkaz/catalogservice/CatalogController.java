package com.herokuapp.samkaz.catalogservice;

import com.herokuapp.samkaz.payload.response.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CatalogController {

    @Autowired
    PromotionServiceClient promotionServiceClient;

    @PreAuthorize("@securityService.hasScope('read_catalog')")
    @GetMapping("/products")
    public List<ProductDTO> products() {
        List<ProductDTO> productDTOs = new ArrayList<>();
        List<Product> products = getProducts();
        for (Product product : products) {
            String promotion = null;
            try {
                promotion = promotionServiceClient.getPromotionByProductId(product.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
            productDTOs.add(new ProductDTO(product.getId(),product.getName(), product.getPrice(), promotion));
        }
        return productDTOs;
    }

    @PreAuthorize("@securityService.hasScope('read_catalog')")
    private List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1L,"Pepsi", 25.00));
        products.add(new Product(2L,"CocaCola", 35.00));
        return products;
    }
}
