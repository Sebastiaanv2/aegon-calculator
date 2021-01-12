package nl.aegon.calculator.service;

import nl.aegon.calculator.exceptions.CalculationException;
import nl.aegon.calculator.model.Calculation;
import nl.aegon.calculator.model.Operator;
import nl.aegon.calculator.model.SimpleCalculator;
import nl.aegon.calculator.repository.CalculatorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

@SpringBootTest
class CalculatorServiceTest {

    private Calculation testCalculation;

    @MockBean
    SimpleCalculator simpleCalculatorMock;

    @MockBean
    CalculatorRepository calculatorRepositoryMock;

    private CalculatorService calculatorService;

    @BeforeEach
    public void beforeEach() {
        calculatorService = new CalculatorService(calculatorRepositoryMock, simpleCalculatorMock);
        testCalculation = new Calculation(1L, Operator.Add, 1, 2, 0.0d);
    }

    @Test
    public void calculate_WhenOperatorIsAdd_ShouldCallAddOnSimpleCalculator() throws CalculationException {
        testCalculation.setOperator(Operator.Add);
        calculatorService.calculate(testCalculation);
        Mockito.verify(simpleCalculatorMock, times(1)).add(testCalculation.getValue1(), testCalculation.getValue2());
    }

    @Test
    public void calculate_WhenOperatorIsSubtract_ShouldCallSubtractOnSimpleCalculator() throws CalculationException {
        testCalculation.setOperator(Operator.Subtract);
        calculatorService.calculate(testCalculation);
        Mockito.verify(simpleCalculatorMock, times(1)).subtract(testCalculation.getValue1(), testCalculation.getValue2());
    }

    @Test
    public void calculate_WhenOperatorIsDivide_ShouldCallDivideOnSimpleCalculator() throws CalculationException {
        testCalculation.setOperator(Operator.Divide);
        calculatorService.calculate(testCalculation);
        Mockito.verify(simpleCalculatorMock, times(1)).divide(testCalculation.getValue1(), testCalculation.getValue2());
    }

    @Test
    public void calculate_WhenOperatorIsMultiply_ShouldCallMultiplyOnSimpleCalculator() throws CalculationException {
        testCalculation.setOperator(Operator.Multiply);
        calculatorService.calculate(testCalculation);
        Mockito.verify(simpleCalculatorMock, times(1)).multiply(testCalculation.getValue1(), testCalculation.getValue2());
    }

    @Test
    public void getCalculationHistory() {
    }
}