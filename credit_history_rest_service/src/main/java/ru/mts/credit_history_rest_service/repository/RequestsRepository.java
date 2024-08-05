package ru.mts.credit_history_rest_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mts.credit_history_rest_service.model.RequestForChecks;

import java.util.List;

@Repository
public interface RequestsRepository extends JpaRepository<RequestForChecks, String> {
    List<RequestForChecks> findByStatus(String status);
}