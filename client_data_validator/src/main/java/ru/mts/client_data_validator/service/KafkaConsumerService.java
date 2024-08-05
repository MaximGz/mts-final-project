package ru.mts.client_data_validator.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.mts.client_data_validator.model.Account;
import ru.mts.client_data_validator.model.Client;
import ru.mts.client_data_validator.model.ValidationResult;
import ru.mts.client_data_validator.repository.AccountRepository;
import ru.mts.client_data_validator.repository.ClientRepository;
import ru.mts.data_models.ClientData;
import ru.mts.data_models.OptyData;
import ru.mts.data_models.StatusData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@Log4j2
@Service
public class KafkaConsumerService {
    @Autowired
    private ClientDataValidationService clientDataValidationService;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private KafkaTemplate<String, StatusData> kafkaTemplate;

    @KafkaListener(topics = "client_data_validator_send", groupId = "process-group2")
    public void listen(OptyData optyData) throws InterruptedException {
        log.info("Пришло сообщение о проверке по заявке: " + optyData.getId());
        StatusData statusData = new StatusData();
        statusData.setId(optyData.getId());
        ValidationResult validationResult = clientDataValidationService.validateAndStoreInDatabase(optyData);
        if (validationResult.isValid()) {
            statusData.setStatus("CLIENT_VALID");
            statusData.setComment("Проверка клиента пройдена успешна!");
            log.info("Проверка данных по заявке: " + optyData.getId() + " успешна.");
        } else {
            statusData.setStatus("CLIENT_INVALID");
            if (validationResult.getFails() != -1 && validationResult.getFails() > 0) {
                statusData.setComment("Клиент найден по ключевым данным, но в доп информации " + validationResult.getFails()
                                      + " ошибки (ошибок). В полях: " + validationResult.getFailMessage());
            } else {
                statusData.setComment("Клиент не найден в базе данных банка!");
            }
            log.info("Проверка данных по заявке: " + optyData.getId() + " завершилась с ошибкой.");
        }
        Thread.sleep(10000);
        kafkaTemplate.send("client_data_validator_receive", optyData.getId(), statusData);
    }

    @KafkaListener(topics = "create_account", groupId = "process-group10")
    public void listen1(OptyData optyData) {
        ClientData clientData = optyData.getClientData();

        Optional<Client> clientOpt = clientRepository.findByPassportSeriaAndPassportNumberAndFirstNameAndLastNameAndMiddleNameAndBirthDate(
                clientData.getPassportSeria(),
                clientData.getPassportNumber(),
                clientData.getFirstName(),
                clientData.getLastName(),
                clientData.getMiddleName(),
                clientData.getBirthDate()
        );

        if (clientOpt.isPresent()) {
            Client client = clientOpt.get();

            Account account = new Account();
            account.setId(UUID.randomUUID().toString());
            account.setClient(client);
            account.setCreated(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")));

            accountRepository.save(account);
            log.info("Для клиента с Id: " + client.getId() + " создан новый счёт: " + account.getId());
        } else {
            log.info("Клиент не найден!");
        }
    }
}