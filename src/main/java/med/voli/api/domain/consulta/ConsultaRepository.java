package med.voli.api.domain.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    Boolean existsByMedicoIdAndData(Long medicId, LocalDateTime date);

    Boolean existsByPacienteIdAndDataBetween(Long aLong, LocalDateTime firstAppointment, LocalDateTime lastAppointment);
}
