package tech.leonam.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.leonam.erp.model.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {}
