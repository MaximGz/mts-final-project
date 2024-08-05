package ru.mts.client_data_validator.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mts.client_data_validator.model.Client;
import ru.mts.client_data_validator.model.ClientCheck;
import ru.mts.client_data_validator.model.ValidationResult;
import ru.mts.client_data_validator.repository.ClientChecksRepository;
import ru.mts.client_data_validator.repository.ClientRepository;
import ru.mts.data_models.ClientData;
import ru.mts.data_models.OptyData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Log4j2
@Service
public class ClientDataValidationService {
    @Autowired
    public ClientChecksRepository clientChecksRepository;
    @Autowired
    public ClientRepository clientRepository;

    public ValidationResult validateAndStoreInDatabase(OptyData optyData) {
        String optyId = optyData.getId();
        ClientData clientData = optyData.getClientData();

        // Получение данных из базы данных на основе уникальных атрибутов
        Optional<Client> entityFromDbOptional = clientRepository.findByPassportSeriaAndPassportNumberAndFirstNameAndLastNameAndMiddleNameAndBirthDate(
                clientData.getPassportSeria(),
                clientData.getPassportNumber(),
                clientData.getFirstName(),
                clientData.getLastName(),
                clientData.getMiddleName(),
                clientData.getBirthDate()
        );

        // Проверка наличия записи в базе данных
        if (entityFromDbOptional.isEmpty()) {
            // Если запись отсутствует
            log.info("Клиент по заявке: " + optyId + " не найден в базе");
            return new ValidationResult(false);
        } else {
            // Сравнение данных
            Client entityFromDb = entityFromDbOptional.get();
            return compareData(clientData, entityFromDb, optyId);
        }
    }

    private ValidationResult compareData(ClientData clientData, Client entityFromDb, String optyId) {
        String[] fieldChecks = new String[]{
                "Имя", "Фамилия", "Отчество", "Дата рождения", "Тип паспорта", "Номер паспорта",
                "Серия паспорта", "Пол", "Семейное положение", "Национальность", "Номер телефона"
        };

        boolean[] checks = new boolean[]{
                entityFromDb.getFirstName().equals(clientData.getFirstName()),
                entityFromDb.getLastName().equals(clientData.getLastName()),
                entityFromDb.getMiddleName().equals(clientData.getMiddleName()),
                entityFromDb.getBirthDate().equals(clientData.getBirthDate()),
                entityFromDb.getPassportType().equals(clientData.getPassportType()),
                entityFromDb.getPassportNumber().equals(clientData.getPassportNumber()),
                entityFromDb.getPassportSeria().equals(clientData.getPassportSeria()),
                entityFromDb.getGender().equals(clientData.getGender()),
                entityFromDb.getMaritalStatus().equals(clientData.getMaritalStatus()),
                entityFromDb.getNationality().equals(clientData.getNationality()),
                entityFromDb.getOMT().equals(clientData.getOMT()),
        };

        int fails = 0;
        StringBuilder failMessageBuilder = new StringBuilder();

        ClientCheck clientCheck = new ClientCheck();
        clientCheck.setOptyId(optyId);
        clientCheck.setCreated(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")));
        clientCheck.setFirstName(checks[0]);
        clientCheck.setLastName(checks[1]);
        clientCheck.setMiddleName(checks[2]);
        clientCheck.setBirthDate(checks[3]);
        clientCheck.setPassportNumber(checks[4]);
        clientCheck.setPassportSeria(checks[5]);
        clientCheck.setGender(checks[6]);
        clientCheck.setMaritalStatus(checks[7]);
        clientCheck.setNationality(checks[8]);
        clientCheck.setOMT(checks[9]);

        // Сохраняем объект ClientCheck
        clientChecksRepository.save(clientCheck);

        for (int i = 0; i < checks.length; i++) {
            if (!checks[i]) {
                fails++;
                if (!failMessageBuilder.isEmpty()) {
                    failMessageBuilder.append(", ");
                }
                failMessageBuilder.append(fieldChecks[i]);
            }
        }

        boolean isValid = fails == 0;
        // Возвращаем результат проверки
        return new ValidationResult(isValid, fails, failMessageBuilder.toString());
    }
}
