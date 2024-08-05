package ru.mts.client_data_validator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mts.client_data_validator.model.ClientCheck;

public interface ClientChecksRepository extends JpaRepository<ClientCheck, String> {
}
