package ru.mts.opty_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mts.opty_service.model.OptyClient;

public interface OptyClientRepository extends JpaRepository<OptyClient, String> {
}