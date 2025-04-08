package med.voli.api.domain.consulta;

import med.voli.api.domain.consulta.dto.CreateConsultaDto;
import med.voli.api.domain.consulta.dto.ResponseCreateConsultaDto;
import med.voli.api.domain.medico.Especialidade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<CreateConsultaDto> jsonCreateConsulta;

    @Autowired
    private JacksonTester<ResponseCreateConsultaDto> jsonResponseCreateConsulta;

    @MockBean
    private ConsultaService consultaService;

    @Test
    @DisplayName("Should return 400 when create a Consulta with invalid data")
    @WithMockUser
    void create_Scenario1() throws Exception {
        var response = mockMvc.perform(post("/consulta"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should return 200 when create a Consulta with invalid data")
    @WithMockUser
    void create_Scenario2() throws Exception {
        var data = LocalDateTime.now().plusHours(1);
        var especialidade = Especialidade.CARDIOLOGIA;

        var responseCreateDto = new ResponseCreateConsultaDto(1L, 2L, 5L, data);
        when(consultaService.create(any())).thenReturn(responseCreateDto);

        var response = mockMvc.perform(
                post("/consulta")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonCreateConsulta.write(
                                new CreateConsultaDto(2L, especialidade, 5L, data)
                        ).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonExpected = jsonResponseCreateConsulta.write(
                responseCreateDto
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonExpected);
    }

}