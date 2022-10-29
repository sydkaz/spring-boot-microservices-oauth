package com.herokuapp.samkaz.promotionservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Promotion {
    private Long id;
    private Long productId;
    private String promoText;
}
