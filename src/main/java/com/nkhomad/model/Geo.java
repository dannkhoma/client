
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
public class Geo implements Serializable {

    private String lat;
    private String lng;
    private final static long serialVersionUID = 5754820180762541454L;

}
