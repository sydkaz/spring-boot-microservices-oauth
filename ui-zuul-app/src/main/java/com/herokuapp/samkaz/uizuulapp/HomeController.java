package com.herokuapp.samkaz.uizuulapp;

import com.herokuapp.samkaz.payload.response.ApiResponse;
import com.herokuapp.samkaz.payload.response.ProductDTO;
import com.herokuapp.samkaz.payload.response.PromotionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    OAuth2RestTemplate restTemplate;

    @Autowired
    ApiResponse apiResponse;

    @Value("${catalog.api:http://localhost:8181/catalog/products}")
    String catalogApi;

    @Value("${promotion.api:http://localhost:8282/promotions/promotions}")
    String promotionApi;

    @GetMapping("/")
    public String index(Model model)
    {
        /*
        try {
            ResponseEntity<String> resp = restTemplate.getForEntity("http://localhost:8080/ui/api/catalog/products", String.class);
            System.err.println("Rsp: "+resp.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
        */


        return "index";
    }

    @ResponseBody
    @GetMapping("/products")
    public ApiResponse getProducts(Model model)
    {
        ResponseEntity<ProductDTO[]> productResp = restTemplate.getForEntity(catalogApi, ProductDTO[].class);
        List<ProductDTO> productDTOS = Arrays.asList(productResp.getBody());
        model.addAttribute("products", productDTOS);
        apiResponse.setMessage(productDTOS);
        apiResponse.setSuccess(true);
        return apiResponse;
    }

    @ResponseBody
    @GetMapping("/promotions")
    public ApiResponse getPromotions(Model model)
    {
        ResponseEntity<PromotionDTO[]> promotionResp = restTemplate.getForEntity(promotionApi, PromotionDTO[].class);
        List<PromotionDTO> promotionDTOS = Arrays.asList(promotionResp.getBody());
        model.addAttribute("promotions", promotionDTOS);
        apiResponse.setMessage(promotionDTOS);
        apiResponse.setSuccess(true);
        return apiResponse;
    }
}


