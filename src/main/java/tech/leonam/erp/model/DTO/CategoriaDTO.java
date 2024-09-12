package tech.leonam.erp.model.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoriaDTO {
    @NotBlank
    @Size(min = 3, max = 255, message = "Verifique o tamanho do nome.")
    private String nome;
    @NotBlank
    @Size(min = 3, max = 510, message = "Verifique o tamanho da descrição")
    private String descricao;
    @NotBlank(message = "Verifique o campo de \"ativo\".")
    private Boolean ativo;
}
