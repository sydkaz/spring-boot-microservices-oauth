package com.herokuapp.samkaz.payload.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Syed
 */

@Component
@Scope(value= BeanDefinition.SCOPE_PROTOTYPE)
@Getter
@Setter
@NoArgsConstructor
public class ApiResponse {
    private Boolean success;
    private Object message;

}