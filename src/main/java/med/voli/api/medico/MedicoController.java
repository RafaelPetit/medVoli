package med.voli.api.medico;

import jakarta.validation.Valid;
import med.voli.api.medico.dto.CreateMedicoDto;
import med.voli.api.medico.dto.ResponseListMedicoDto;
import med.voli.api.medico.dto.UpdateMedicoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


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
    public Page<ResponseListMedicoDto> getAll(@PageableDefault(size = 10, sort={"nome"}) Pageable pageable) {
        return repository.findAllByStatusTrue(pageable)
                .map(ResponseListMedicoDto::new);
    }

    @PatchMapping
    @Transactional
    public void update(@RequestBody @Valid UpdateMedicoDto medico) {
       repository.findById(medico.id())
               .ifPresent(m -> m.updateData(medico));
    }

    @DeleteMapping("{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        repository.findById(id)
                .ifPresent(m -> m.setStatus(false));
    }
}