package med.voli.api.medico.dto;

import med.voli.api.medico.Especialidade;
import med.voli.api.medico.Medico;

public record ResponseListMedicoDto(String nome, String email, String crm, Especialidade especialidade) {
    public ResponseListMedicoDto(Medico medico) {
        this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
