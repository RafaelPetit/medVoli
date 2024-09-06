package med.voli.api.medico.dto;

import jakarta.validation.constraints.NotNull;
import med.voli.api.endereco.dto.EnderecoDto;

public record UpdateMedicoDto(
        @NotNull
        Long id,
        String nome,
        String telefone,
        EnderecoDto endereco) {
}
