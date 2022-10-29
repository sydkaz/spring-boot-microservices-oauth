package com.herokuapp.samkaz.payload.response;

import lombok.Data;

@Data
public
class PromotionDTO {
    Long id;
    Long productId;
    String promoText;
}
