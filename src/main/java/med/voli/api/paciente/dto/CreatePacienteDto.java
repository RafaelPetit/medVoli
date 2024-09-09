package med.voli.api.paciente.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import med.voli.api.endereco.dto.EnderecoDto;
import org.hibernate.validator.constraints.br.CPF;

public record CreatePacienteDto (
        @NotBlank
        @Pattern(regexp = "^[a-zA-ZÀ-ÖØ-öø-ÿ\\s'\\-]{3,}$")
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "^[0-9]{11}$")
        String telefone,
        @CPF
        @NotBlank
        String cpf,
        @Valid
        @NotNull
        EnderecoDto endereco) {
}
