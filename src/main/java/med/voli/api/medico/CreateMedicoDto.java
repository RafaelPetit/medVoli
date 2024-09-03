package med.voli.api.medico;

import med.voli.api.endereco.EnderecoDto;

public record CreateMedicoDto(String nome, String email, String crm, Especialidade especialidade, EnderecoDto endereco) {
}