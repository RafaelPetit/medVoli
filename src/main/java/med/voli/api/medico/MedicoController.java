package med.voli.api.medico;

import jakarta.validation.Valid;
import med.voli.api.medico.dto.CreateMedicoDto;
import med.voli.api.medico.dto.ResponseListMedicoDto;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medico")
public class MedicoController {
    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public Medico create(@RequestBody @Valid CreateMedicoDto medico) {
       return repository.save(new Medico(medico));
    }

    @GetMapping
    public List<ResponseListMedicoDto> list() {
        return repository.findAll().stream().map(ResponseListMedicoDto::new).toList();
    }
}