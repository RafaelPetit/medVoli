package med.voli.api.medico.dto;

import med.voli.api.endereco.Endereco;
import med.voli.api.medico.Especialidade;
import med.voli.api.medico.Medico;


public record ResponseMedicoDto(Long id, String nome, String crm, String telefone, String email, Especialidade especialidade, Endereco endereco) {

    public ResponseMedicoDto(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getCrm(), medico.getTelefone(), medico.getEmail(), medico.getEspecialidade(), medico.getEndereco());
    }
}
