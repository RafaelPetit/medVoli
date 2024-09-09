package med.voli.api.paciente;

import jakarta.validation.Valid;
import med.voli.api.paciente.dto.CreatePacienteDto;
import med.voli.api.paciente.dto.ResponseListPacienteDto;
import med.voli.api.paciente.dto.ResponsePacienteDto;
import med.voli.api.paciente.dto.UpdatePacienteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController()
@RequestMapping("/paciente")
public class PacienteController {
    @Autowired
    private PacienteRepository repository;

    @PostMapping()
    @Transactional
    public ResponseEntity<ResponsePacienteDto> create (@RequestBody @Valid CreatePacienteDto paciente, UriComponentsBuilder uriBuilder) {
        var createdPaciente = repository.save(new Paciente(paciente));

        var uri = uriBuilder.path("/paciente/{id}").buildAndExpand(createdPaciente.getId()).toUri();

        return ResponseEntity.created(uri).body(new ResponsePacienteDto(createdPaciente));
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponsePacienteDto> readOne(@PathVariable Long id) {
        var paciente = repository.findById(id);

        return ResponseEntity.ok(new ResponsePacienteDto(paciente.get()));
    }

    @GetMapping
    public ResponseEntity<Page<ResponseListPacienteDto>> readAll(@PageableDefault(size = 10, sort = {"nome"}) Pageable  pageable) {
        var page = repository.findAllByStatusTrue(pageable)
                .map(ResponseListPacienteDto::new);

        return ResponseEntity.ok(page);
    }

    @PatchMapping
    @Transactional
    public ResponseEntity<ResponsePacienteDto> update(@RequestBody @Valid UpdatePacienteDto paciente) {
        var updatedPaciente = repository.findById(paciente.id());
        updatedPaciente.ifPresent(p -> p.updateData(paciente));

        return ResponseEntity.ok(new ResponsePacienteDto(updatedPaciente.get()));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        repository.findById(id)
            .ifPresent(p -> p.setStatus(false));

        return ResponseEntity.noContent().build();
    }
}
