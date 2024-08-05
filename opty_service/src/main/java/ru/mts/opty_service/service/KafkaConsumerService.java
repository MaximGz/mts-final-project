package ru.mts.opty_service.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.mts.data_models.StatusData;
import ru.mts.opty_service.model.Opty;
import ru.mts.opty_service.repository.OptyRepository;

import java.util.Optional;

@Log4j2
@Service
public class KafkaConsumerService {
    @Autowired
    private OptyRepository optyRepository;

    @KafkaListener(topics = "opty-status", groupId = "opty-status")
    public void listen(StatusData statusData) {
        System.out.println("Получил Id" + statusData.getId());
        Optional<Opty> optyOptional = optyRepository.findById(statusData.getId());

        if (optyOptional.isPresent()) {
            Opty opty = optyOptional.get();
            opty.setStatus(statusData.getStatus());
            opty.setComment(statusData.getComment());

            optyRepository.save(opty);

            log.info("Статус заявки: " + statusData.getId() + " обновлен на " + statusData.getStatus());
        } else {
            log.info("Статус заявки: " + statusData.getId() + " не найден.");
        }
    }
}