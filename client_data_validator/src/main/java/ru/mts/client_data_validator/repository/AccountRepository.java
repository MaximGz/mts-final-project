package ru.mts.client_data_validator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mts.client_data_validator.model.Account;

public interface AccountRepository extends JpaRepository<Account, String> {
}
