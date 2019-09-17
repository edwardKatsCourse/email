package com.example.sendmail.controller;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/swagger-test")
public class SwaggerExampleController {

    @PostMapping("/files")
    public void method(@RequestBody MyModel myModel) {
        System.out.println(myModel);
    }

}

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
class MyModel {

    @ApiModelProperty(dataType = "file", example = "file.txt")
    private String singleFile;

    @ApiModelProperty(dataType = "List<file>", example = "[file.txt,file_2.txt]")
    private List<String> listOfFiles;
}
