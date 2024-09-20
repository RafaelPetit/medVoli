package med.voli.api.domain.consulta.validations;

import jakarta.validation.ValidationException;
import med.voli.api.domain.consulta.dto.CreateConsultaDto;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ArriveTImeValidation implements AppointmentSchedulerValidator {

    public void validate(CreateConsultaDto createConsultaDto) {
        var date = createConsultaDto.data();
        var now = LocalDateTime.now();
        var difference = Duration.between(now, date).toMinutes();

        if (difference < 30) {
            throw new ValidationException("You must arrive at least 30 minutes before the consultation");
        }
    }
}