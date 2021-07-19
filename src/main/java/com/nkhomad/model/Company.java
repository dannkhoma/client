
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
public class Company implements Serializable {
    private final static long serialVersionUID = -2803675965500174697L;
    private String name;
    private String catchPhrase;
    private String bs;

}
