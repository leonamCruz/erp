package tech.leonam.erp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import tech.leonam.erp.model.entity.Servico;
import tech.leonam.erp.model.enums.StatusServico;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
    Page<Servico> findAllByStatus(StatusServico status, Pageable pageable);
}
