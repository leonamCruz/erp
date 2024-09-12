package tech.leonam.erp.model.DTO;

import jakarta.validation.constraints.NotBlank;

public class CategoriaDTO {
    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;
    @NotBlank
    private Boolean ativo;
}
