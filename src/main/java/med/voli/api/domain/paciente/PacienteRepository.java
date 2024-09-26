package med.voli.api.domain.paciente;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Page<Paciente> findAllByStatusTrue(Pageable pageable);

    @Query("""
            select p.status
            from Paciente p
            where p.id = :id
            """)
    Boolean findActiveById(Long id);
}
