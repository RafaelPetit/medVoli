package med.voli.api.domain.consulta;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voli.api.domain.consulta.dto.CreateConsultaDto;
import med.voli.api.domain.consulta.dto.ResponseCreateConsultaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consulta")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseCreateConsultaDto> create(@RequestBody @Valid CreateConsultaDto createConsultaDto) {
        var createdAppointment = consultaService.create(createConsultaDto);

        return ResponseEntity.ok(createdAppointment);
    }

}
