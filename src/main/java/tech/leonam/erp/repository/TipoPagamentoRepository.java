package tech.leonam.erp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tech.leonam.erp.model.DTO.responseApi.TipoPagamentoNomesDTO;
import tech.leonam.erp.model.entity.TipoPagamento;

public interface TipoPagamentoRepository extends JpaRepository<TipoPagamento, Long> {
    @Query("SELECT DISTINCT new tech.leonam.erp.model.DTO.responseApi.TipoPagamentoNomesDTO(t.id, t.nome) FROM TipoPagamento t")
    public List<TipoPagamentoNomesDTO> findAllTipoPagamentoNomesDTOs();
}
