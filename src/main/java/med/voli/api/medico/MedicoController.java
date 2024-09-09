package med.voli.api.medico;

import jakarta.validation.Valid;
import med.voli.api.medico.dto.CreateMedicoDto;
import med.voli.api.medico.dto.ResponseListMedicoDto;
import med.voli.api.medico.dto.ResponseUpdateMedicoDto;
import med.voli.api.medico.dto.UpdateMedicoDto;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;


@RestController
@RequestMapping("/medico")
public class MedicoController {
    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseUpdateMedicoDto> create(@RequestBody @Valid CreateMedicoDto medico, UriComponentsBuilder uriBuilder) {
        var createdMedico = repository.save(new Medico(medico));

        var uri = uriBuilder.path("/medico/{id}").buildAndExpand(createdMedico.getId()).toUri();

        return ResponseEntity.created(uri).body(new ResponseUpdateMedicoDto(createdMedico));
    }

    @GetMapping
    public ResponseEntity<Page<ResponseListMedicoDto>> getAll(@PageableDefault(size = 10, sort={"nome"}) Pageable pageable) {
        var page = repository.findAllByStatusTrue(pageable)
                .map(ResponseListMedicoDto::new);

        return ResponseEntity.ok(page);

    }

    @PatchMapping
    @Transactional
    public ResponseEntity<ResponseUpdateMedicoDto> update(@RequestBody @Valid UpdateMedicoDto medico) {
        var updatedMedico = repository.findById(medico.id());
        updatedMedico.ifPresent(m -> m.updateData(medico));

        return ResponseEntity.ok(new ResponseUpdateMedicoDto(updatedMedico.orElseThrow(() -> new RuntimeException("Medico not found"))));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        repository.findById(id)
                .ifPresent(m -> m.setStatus(false));

        return ResponseEntity.noContent().build();
    }
}