package com.herokuapp.samkaz.catalogservice;

import com.herokuapp.samkaz.payload.response.PromotionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PromotionServiceClient {

    @Autowired
    OAuth2RestTemplate restTemplate;

    String url = "http://localhost:8282/promotions/products/{id}/promotion";

    public String getPromotionByProductId(Long id) {
        System.out.println("Fetching promotion for ProductID: "+id);
        ResponseEntity<PromotionDTO> resp = restTemplate.getForEntity(url, PromotionDTO.class, id);
        return resp.getBody().getPromoText();
    }
}

