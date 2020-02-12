package cl.roisel.cdd.app.transbank.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tbk-service")
public class TransbankController {

    @GetMapping
    public String init(){
        return "";
    }
}
