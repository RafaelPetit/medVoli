package med.voli.api.domain.medico;

import med.voli.api.domain.consulta.Consulta;
import med.voli.api.domain.endereco.dto.EnderecoDto;
import med.voli.api.domain.medico.dto.CreateMedicoDto;
import med.voli.api.domain.paciente.Paciente;
import med.voli.api.domain.paciente.dto.CreatePacienteDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    @DisplayName("Returns null when Medic is not found on available date")
    void findRandomByAvailableMedicOnDate_Scenario() {
        //given ou arrange
        var nextMondayAt10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        var medico = cadastrarMedico("medico", "medico@gmail.com", "123456", Especialidade.CARDIOLOGIA);
        var paciente = cadastrarPaciente("paciente", "paciente@gmail.com", "123456789111");
        cadastrarConsulta(medico, paciente, nextMondayAt10);

        //when ou act
        var medic = medicoRepository.findRandomByAvailableMedicOnDate(Especialidade.CARDIOLOGIA, nextMondayAt10);

        //then ou assert
        assertThat(medic).isNull();
    }

    @Test
    @DisplayName("Returns Medic  when Medic is found on available date")
    void findRandomByAvailableMedicOnDate_Scenario2() {
        //arrange
        var nextMondayAt10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        var createMedic = cadastrarMedico("medico", "medico@gmail.com", "123456", Especialidade.CARDIOLOGIA);

        //act
        var medic = medicoRepository.findRandomByAvailableMedicOnDate(Especialidade.CARDIOLOGIA, nextMondayAt10);

        //assert
        assertThat(medic).isEqualTo(createMedic);
    }

    @Test
    @DisplayName("Returns null when no Medico is available for the given date and specialty")
    void findRandomByAvailableMedicOnDate_NoMedicoAvailable() {
        // Arrange
        var nextMondayAt10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        // Act
        var medic = medicoRepository.findRandomByAvailableMedicOnDate(Especialidade.CARDIOLOGIA, nextMondayAt10);

        // Assert
        assertThat(medic).isNull();
    }

    @Test
    @DisplayName("Returns Medico when multiple Medicos are available for the given date and specialty")
    void findRandomByAvailableMedicOnDate_MultipleMedicosAvailable() {
        // Arrange
        var nextMondayAt10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        var medico1 = cadastrarMedico("medico1", "medico1@gmail.com", "123456", Especialidade.CARDIOLOGIA);
        var medico2 = cadastrarMedico("medico2", "medico2@gmail.com", "654321", Especialidade.CARDIOLOGIA);

        // Act
        var medic = medicoRepository.findRandomByAvailableMedicOnDate(Especialidade.CARDIOLOGIA, nextMondayAt10);

        // Assert
        assertThat(medic).isIn(medico1, medico2);
    }

    @Test
    @DisplayName("Returns null when Medico is not available due to another consultation at the same time")
    void findRandomByAvailableMedicOnDate_MedicoNotAvailableDueToConsultation() {
        // Arrange
        var nextMondayAt10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        var medico = cadastrarMedico("medico", "medico@gmail.com", "123456", Especialidade.CARDIOLOGIA);
        var paciente = cadastrarPaciente("paciente", "paciente@gmail.com", "123456789111");
        cadastrarConsulta(medico, paciente, nextMondayAt10);

        // Act
        var medic = medicoRepository.findRandomByAvailableMedicOnDate(Especialidade.CARDIOLOGIA, nextMondayAt10);

        // Assert
        assertThat(medic).isNull();
    }

    @Test
    @DisplayName("Returns Medico when Medico is available for a different time on the same day")
    void findRandomByAvailableMedicOnDate_MedicoAvailableForDifferentTime() {
        // Arrange
        var nextMondayAt10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        var nextMondayAt11 = nextMondayAt10.plusHours(1);
        var medico = cadastrarMedico("medico", "medico@gmail.com", "123456", Especialidade.CARDIOLOGIA);
        var paciente = cadastrarPaciente("paciente", "paciente@gmail.com", "123456789111");
        cadastrarConsulta(medico, paciente, nextMondayAt11);

        // Act
        var medic = medicoRepository.findRandomByAvailableMedicOnDate(Especialidade.CARDIOLOGIA, nextMondayAt10);

        // Assert
        assertThat(medic).isEqualTo(medico);
    }




    private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        testEntityManager.persist(new Consulta(null, medico, paciente, data));
    }

    private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
        var medico = new Medico(createMedicoDto(nome, email, crm, especialidade));
        testEntityManager.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(String nome, String email, String cpf) {
        var paciente = new Paciente(createPacienteDto(nome, email, cpf));
        testEntityManager.persist(paciente);
        return paciente;
    }

    private CreateMedicoDto createMedicoDto(String nome, String email, String crm, Especialidade especialidade) {
        return new CreateMedicoDto(
                nome,
                email,
                "61999999999",
                crm,
                especialidade,
                enderecoDto()
        );
    }

    private CreatePacienteDto createPacienteDto(String nome, String email, String cpf) {
        return new CreatePacienteDto(
                nome,
                email,
                "61999999999",
                cpf,
                enderecoDto()
        );
    }

    private EnderecoDto enderecoDto() {
        return new EnderecoDto(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }

}