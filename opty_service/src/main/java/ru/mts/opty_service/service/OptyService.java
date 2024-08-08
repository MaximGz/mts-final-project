package ru.mts.opty_service.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.mts.data_models.ClientData;
import ru.mts.data_models.OptyData;
import ru.mts.opty_service.mapper.OptyDataMapper;
import ru.mts.opty_service.model.Opty;
import ru.mts.opty_service.model.OptyClient;
import ru.mts.opty_service.repository.OptyClientRepository;
import ru.mts.opty_service.repository.OptyRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class OptyService {
    @Autowired
    private OptyRepository optyRepository;
    @Autowired
    private KafkaTemplate<String, OptyData> kafkaTemplate;
    @Autowired
    private OptyClientRepository optyClientRepository;
    @Autowired
    private OptyDataMapper optyDataMapper;

    @Transactional
    public void createOpty(ClientData clientData) {
        System.out.println(clientData);
        String optyId = UUID.randomUUID().toString();
        String receivedStatus = "RECEIVED";

        OptyClient optyClient = optyDataMapper.toOptyClient(clientData);

        optyClientRepository.save(optyClient);

        Opty opty = new Opty();
        opty.setStatus(receivedStatus);
        opty.setId(optyId);
        opty.setCreated(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")));
        opty.setClient(optyClient);

        optyRepository.save(opty);

        OptyData optyData = new OptyData();
        optyData.setId(optyId);
        optyData.setStatus(receivedStatus);
        optyData.setClientData(clientData);
        kafkaTemplate.send("process_service", optyId, optyData);
    }
}