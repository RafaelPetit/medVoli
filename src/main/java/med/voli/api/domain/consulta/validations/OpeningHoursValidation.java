package med.voli.api.domain.consulta.validations;

import jakarta.validation.ValidationException;
import med.voli.api.domain.consulta.dto.CreateConsultaDto;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class OpeningHoursValidation implements AppointmentSchedulerValidator {

    public void validate(CreateConsultaDto createConsultaDto) {
        var date = createConsultaDto.data();

        var sunday = date.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var beforeOpening = date.getHour() < 8;
        var afterClosing = date.getHour() > 18;

        if (sunday || beforeOpening || afterClosing) {
            throw new ValidationException("Consultas are only available from Monday to Saturday, from 8 to 18");
        }


    }
}
