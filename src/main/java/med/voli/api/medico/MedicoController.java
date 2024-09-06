package med.voli.api.medico;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
