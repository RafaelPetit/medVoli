package med.voli.api.domain.medico.dto;

import med.voli.api.domain.medico.Especialidade;
import med.voli.api.domain.medico.Medico;

public record ResponseListMedicoDto(Long id, String nome, String email, String crm, Especialidade especialidade) {
    public ResponseListMedicoDto(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
