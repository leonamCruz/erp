package tech.leonam.erp.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tech.leonam.erp.model.DTO.EstoqueDTO;
import tech.leonam.erp.model.entity.Estoque;
import tech.leonam.erp.repository.EstoqueRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class EstoqueService {
    private final EstoqueRepository estoqueRepository;
    private final ModelMapper modelMapper;

    public void salvarEstoque(EstoqueDTO estoqueDTO) {
        Estoque estoque = modelMapper.map(estoqueDTO, Estoque.class);
        estoqueRepository.save(estoque);
    }

    public void deletarEstoquePorId(Long id) {
        estoqueRepository.deleteById(id);
    }

    public List<Estoque> retornaTodoEstoque(){
        return estoqueRepository.findAll();
    }

    public void alteraEstoque(EstoqueDTO estoqueDTO, Long id) {
        Estoque estoque = modelMapper.map(estoqueDTO, Estoque.class);
        estoque.setId(id);

        estoqueRepository.save(estoque);
    }

}
