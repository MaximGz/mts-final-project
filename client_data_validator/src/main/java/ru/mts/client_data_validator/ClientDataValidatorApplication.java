package ru.mts.client_data_validator;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.mts.client_data_validator.model.Client;
import ru.mts.client_data_validator.repository.ClientRepository;

import java.util.Optional;

@SpringBootApplication
public class ClientDataValidatorApplication {
	@Autowired
	ClientRepository clientRepository;

	public static void main(String[] args) {
		SpringApplication.run(ClientDataValidatorApplication.class, args);
	}
}
