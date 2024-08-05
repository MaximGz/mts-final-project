package ru.mts.opty_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mts.data_models.ClientData;
import ru.mts.opty_service.service.OptyService;

@RestController
@RequestMapping("/optys")
public class OptyController {
    @Autowired
    private OptyService optyService;

    @PostMapping
    public ResponseEntity<String> createOpty(@RequestBody ClientData clientData) {
        System.out.println(clientData);
        optyService.createOpty(clientData);
        return ResponseEntity.ok("Opty received");
    }
}
