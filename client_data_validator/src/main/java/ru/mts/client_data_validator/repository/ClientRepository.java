package ru.mts.client_data_validator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mts.client_data_validator.model.Client;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, String> {
    Optional<Client> findByPassportSeriaAndPassportNumberAndFirstNameAndLastNameAndMiddleNameAndBirthDate(
            String passportSeria,
            String passportNumber,
            String firstName,
            String lastName,
            String middleName,
            String birthDate
    );
}
