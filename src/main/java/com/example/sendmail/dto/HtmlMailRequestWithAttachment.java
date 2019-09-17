package com.example.sendmail.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HtmlMailRequestWithAttachment {

    @ApiModelProperty(example = "your.email@site.com")
    private String sendTo;

    @ApiModelProperty(example = "Message with HTML subject")
    private String subject;

    @ApiModelProperty(example = "<h1 style='color: red'>This is my body</h1>")
    private String htmlBody;

    @ApiModelProperty(example = "logo.jpg", dataType = "file")
    private String base64Attachment;
}
