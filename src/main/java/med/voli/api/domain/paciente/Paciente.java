package med.voli.api.domain.paciente;

import jakarta.persistence.*;
import lombok.*;
import med.voli.api.domain.endereco.Endereco;
import med.voli.api.domain.paciente.dto.CreatePacienteDto;
import med.voli.api.domain.paciente.dto.UpdatePacienteDto;

@Entity(name = "Paciente")
@Table(name = "pacientes")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private boolean status = true;

    @Embedded
    Endereco endereco;

    public Paciente(CreatePacienteDto paciente) {
        this.nome = paciente.nome();
        this.email = paciente.email();
        this.telefone = paciente.telefone();
        this.cpf = paciente.cpf();
        this.endereco = new Endereco(paciente.endereco());
    }

    public void updateData(UpdatePacienteDto paciente) {
        if (paciente.nome() != null) {
            this.nome = paciente.nome();
        }
        if (paciente.telefone() != null) {
            this.telefone = paciente.telefone();
        }
        if (paciente.endereco() != null) {
            this.endereco.updateData(paciente.endereco());
        }
    }
}
