package nl.aegon.calculator.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.aegon.calculator.model.Calculation;
import nl.aegon.calculator.model.Operator;
import nl.aegon.calculator.service.CalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CalculatorController.class)
class CalculatorControllerTest {

    @MockBean
    private CalculatorService calculatorService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    private Calculation testCalculation;

    @BeforeEach
    public void beforeEach() {
        testCalculation = new Calculation(1L, Operator.Add, 1, 2, 3.0d);
    }

    @Test
    public void getCalculationHistory_ShouldReturnCalculationList_WhenOneResult() throws Exception {
        when(calculatorService.getCalculationHistory()).thenReturn(List.of(testCalculation));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/calculation"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"operator\":\"Add\",\"value1\":1,\"value2\":2,\"result\":3.0}]"));
    }

    @Test
    public void getCalculationHistory_ShouldReturnNoResult_WhenResultIsNull() throws Exception {
        when(calculatorService.getCalculationHistory()).thenReturn(null);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/calculation"))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    public void calculate_ShouldReturnResult_WhenProperCalculationObjectIsPassed() throws Exception {
        when(calculatorService.calculate(any(Calculation.class))).thenReturn(testCalculation);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/calculation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testCalculation)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"operator\":\"Add\",\"value1\":1,\"value2\":2,\"result\":3.0}"));
    }

    @Test
    public void calculate_ShouldReturn400_WhenResultOrGivenCalculationIsBad() throws Exception {
        when(calculatorService.calculate(any(Calculation.class))).thenReturn(null);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/calculation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testCalculation)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").doesNotExist());
    }

}