
package com.nkhomad.model;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address implements Serializable {
    private final static long serialVersionUID = 3764807987013157194L;
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;


}
