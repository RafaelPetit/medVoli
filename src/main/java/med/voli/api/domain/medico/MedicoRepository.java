package med.voli.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
   Page<Medico> findAllByStatusTrue(Pageable pageable);

    @Query(value = """
            select m from Medico m
            where
            m.status = true
            and m.especialidade = :especialidade
            and
            m.id not in (
                select c.medico.id from Consulta c
                where c.data = :data
            )
            order by rand()
            limit 1
            """, nativeQuery = true)
    Medico findFirstByEspecialidade(Especialidade especialidade, LocalDateTime data);

    @Query(value = """
            select m from Medico m
            where
            m.status = true
            and m.id = :id
            """, nativeQuery = true)
    Boolean findActiveById(Long id);
}
