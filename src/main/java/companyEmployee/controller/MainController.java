package companyEmployee.controller;


import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;


@Controller
public class MainController {

    @Value("${employee.upload.path}")
    private String imagePath;


    @GetMapping("/")
    public String main() {
        return "index";
    }

    @GetMapping("/getImage")
    public @ResponseBody byte[] getImage(@RequestParam("picName") String picName) throws IOException {
        InputStream inputStream = new FileInputStream(imagePath + picName);
        return IOUtils.toByteArray(inputStream);


    }


}
