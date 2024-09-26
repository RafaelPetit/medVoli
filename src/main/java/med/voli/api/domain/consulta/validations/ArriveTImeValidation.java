package med.voli.api.domain.consulta.validations;

import med.voli.api.domain.consulta.dto.CreateConsultaDto;
import med.voli.api.infra.exception.ValidationException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ArriveTImeValidation implements AppointmentSchedulerValidator {

    public void validate(CreateConsultaDto createConsultaDto) {
        var date = createConsultaDto.data();
        var now = LocalDateTime.now();
        var difference = Duration.between(now, date).toMinutes();

        if(date.isBefore(now)) {
            throw new ValidationException("The date must be in the future");
        }

        if (difference < 30) {
            throw new ValidationException("You must arrive at least 30 minutes before the appointment");
        }
    }
}