package ru.mts.opty_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mts.opty_service.model.Opty;

@Repository
public interface OptyRepository extends JpaRepository<Opty, String> {
}