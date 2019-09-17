package com.example.sendmail.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SimpleMailRequest {

    @ApiModelProperty(example = "your.email@site.com")
    private String sendTo;

    @ApiModelProperty(example = "Message header")
    private String subject;

    @ApiModelProperty(example = "Message body")
    private String message;
}
