package med.voli.api.domain.medico.dto;

import med.voli.api.domain.endereco.Endereco;
import med.voli.api.domain.medico.Medico;
import med.voli.api.domain.medico.Especialidade;


public record ResponseMedicoDto(Long id, String nome, String crm, String telefone, String email, Especialidade especialidade, Endereco endereco) {

    public ResponseMedicoDto(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getCrm(), medico.getTelefone(), medico.getEmail(), medico.getEspecialidade(), medico.getEndereco());
    }
}
