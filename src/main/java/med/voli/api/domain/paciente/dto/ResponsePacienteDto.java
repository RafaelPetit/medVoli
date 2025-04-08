package med.voli.api.domain.paciente.dto;

import med.voli.api.domain.endereco.Endereco;
import med.voli.api.domain.paciente.Paciente;

public record ResponsePacienteDto(
        Long id,
        String nome,
        String email,
        String telefone,
        String cpf,
        Endereco endereco) {
    public ResponsePacienteDto(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco());
    }
}
