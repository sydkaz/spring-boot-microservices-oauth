package com.herokuapp.samkaz.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    Long id;
    String name;
    double price;
    String promotion;
}
