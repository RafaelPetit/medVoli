package med.voli.api.domain.paciente.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import med.voli.api.domain.endereco.dto.EnderecoDto;

public record UpdatePacienteDto(
        @NotNull
        Long id,
        @Pattern(regexp = "^[a-zA-ZÀ-ÖØ-öø-ÿ\\s'\\-]{3,}$")
        String nome,
        @Pattern(regexp = "^[0-9]{11}$")
        String telefone,
        @Valid
        EnderecoDto endereco) {
}
