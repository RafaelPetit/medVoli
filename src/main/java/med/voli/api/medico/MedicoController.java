package med.voli.api.medico;

import jakarta.validation.Valid;
import med.voli.api.medico.dto.CreateMedicoDto;
import med.voli.api.medico.dto.ResponseListMedicoDto;
import med.voli.api.medico.dto.ResponseMedicoDto;
import med.voli.api.medico.dto.UpdateMedicoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/medico")
public class MedicoController {
    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseMedicoDto> create(@RequestBody @Valid CreateMedicoDto medico, UriComponentsBuilder uriBuilder) {
        var createdMedico = repository.save(new Medico(medico));

        var uri = uriBuilder.path("/medico/{id}").buildAndExpand(createdMedico.getId()).toUri();

        return ResponseEntity.created(uri).body(new ResponseMedicoDto(createdMedico));
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseMedicoDto> readOne(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        var medico = repository.findById(id);

        return ResponseEntity.ok(new ResponseMedicoDto(medico.get()));
    }

    @GetMapping
    public ResponseEntity<Page<ResponseListMedicoDto>> readAll(@PageableDefault(size = 10, sort={"nome"}) Pageable pageable) {
        var page = repository.findAllByStatusTrue(pageable)
                .map(ResponseListMedicoDto::new);

        return ResponseEntity.ok(page);

    }

    @PatchMapping
    @Transactional
    public ResponseEntity<ResponseMedicoDto> update(@RequestBody @Valid UpdateMedicoDto medico) throws ChangeSetPersister.NotFoundException {
        var updatedMedico = repository.findById(medico.id());
        updatedMedico.ifPresent(m -> m.updateData(medico));

        return ResponseEntity.ok(new ResponseMedicoDto(updatedMedico.get()));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        repository.findById(id)
                .ifPresent(m -> m.setStatus(false));

        return ResponseEntity.noContent().build();
    }
}