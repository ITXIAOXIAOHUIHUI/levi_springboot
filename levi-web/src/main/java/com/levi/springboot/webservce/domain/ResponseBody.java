package com.levi.springboot.webservce.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author jianghaihui
 * @date 2020/11/13 16:09
 */
@Data
public class ResponseBody {

    @JsonProperty("Message")
    private String message;

    @JsonProperty("IsMoveNext")
    private String isMoveNext;
}
