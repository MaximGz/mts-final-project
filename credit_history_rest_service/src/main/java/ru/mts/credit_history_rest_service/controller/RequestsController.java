package ru.mts.credit_history_rest_service.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mts.credit_history_rest_service.model.RequestForChecks;
import ru.mts.credit_history_rest_service.repository.RequestsRepository;
import ru.mts.credit_history_rest_service.service.RequestsService;

import java.util.List;

@Log4j2
@RestController
@RequestMapping
public class RequestsController {
    @Autowired
    private RequestsService requestsService;
    @Autowired
    private RequestsRepository requestsRepository;

    @GetMapping("/all")
    public List<RequestForChecks> getClientData() {
        return requestsRepository.findByStatus("NEW");
    }

    @PostMapping("/check")
    public ResponseEntity<String> requestHandler(@RequestParam String optyId, @RequestParam String status, @RequestParam String comment) throws InterruptedException {
        log.info("Данные по проверке от менеджера получены по заявке: " + optyId);
        requestsService.updateRequest(optyId, status, comment);
        return ResponseEntity.ok("Requests received");
    }
}
