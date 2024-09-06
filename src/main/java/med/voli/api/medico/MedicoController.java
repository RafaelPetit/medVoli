package med.voli.api.medico;

import jakarta.validation.Valid;
import med.voli.api.medico.dto.CreateMedicoDto;
import med.voli.api.medico.dto.ResponseListMedicoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public void create(@RequestBody @Valid CreateMedicoDto medico) {
       repository.save(new Medico(medico));
    }

    @GetMapping
    public Page<ResponseListMedicoDto> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(ResponseListMedicoDto::new);
    }
}