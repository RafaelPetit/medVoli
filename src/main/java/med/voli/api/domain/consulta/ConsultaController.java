package med.voli.api.domain.consulta;

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
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid CreateConsultaDto createConsultaDto) {
        consultaService.create(createConsultaDto);

        return ResponseEntity.ok(new ResponseCreateConsultaDto(null, null, null, null));
    }

}
