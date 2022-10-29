package com.herokuapp.samkaz.promotionservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import com.herokuapp.samkaz.common.SecurityService;

@RestController
public class PromotionController {
    @Autowired
    private SecurityService securityService;

    private static final Map<Long, Promotion> PROMOTION_MAP = new HashMap<>();

    @PostConstruct
    void init()
    {
        for (int i=1; i<=10; i++)
        {
            String promo = (i % 2 == 0)? "Product-"+i+"-Promotion" : null;
            Promotion p = new Promotion(Long.valueOf(i), Long.valueOf(i),promo);
            PROMOTION_MAP.put(p.getProductId(), p);
        }
    }

    @GetMapping("/products/{id}/promotion")
    public Promotion getPromotionByProductId(@PathVariable("id") Long id) {
        return PROMOTION_MAP.get(id);
    }



    //@PreAuthorize("hasAuthority(\"#oauth2.hasScope('read_promotions')\")")
    //@PreAuthorize("#oauth2.hasScope('read_promotions')")
    @PreAuthorize("@securityService.hasScope('read_promotions')")
    @GetMapping("/promotions")
    public Collection<Promotion> getAllPromotions() {
        return PROMOTION_MAP.values();
    }
}
