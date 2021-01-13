package nl.aegon.calculator.model;

import nl.aegon.calculator.exceptions.CalculationException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SimpleCalculatorTest {

    private static SimpleCalculator simpleCalculator;

    @BeforeAll
    public static void setup() {
        simpleCalculator = new SimpleCalculator();
    }

    @ParameterizedTest
    @CsvSource({
            "5, 5, 10",
            "2147483647, 1, 2147483648"
    })
    public void add_ShouldReturnCorrectResults_WhenGivenSetOfValues(int val1, int val2, double expectedResult) {
        var result = simpleCalculator.add(val1, val2);
        assertEquals(result, expectedResult);
    }

    @ParameterizedTest
    @CsvSource({
            "20, 10, 10",
            "-2147483648, 1, -2147483649"
    })
    public void subtract_ShouldReturnCorrectResults_WhenGivenSetOfValues(int val1, int val2, double expectedResult) {
        var result = simpleCalculator.subtract(val1, val2);
        assertEquals(result, expectedResult);
    }

    @ParameterizedTest
    @CsvSource({
            "10, 0",
            "-10, 0",
    })
    public void divide_ShouldThrowCalculationExceptionInfinity_WhenDividingByZero(int val1, int val2) {
        Exception exception = assertThrows(CalculationException.class, () -> simpleCalculator.divide(val1, val2));
        assertEquals(exception.getMessage(), "Infinity");
    }

    @ParameterizedTest
    @CsvSource({
            "0, 100, 0",
            "15, 5, 3",
    })
    public void divide_ShouldReturnCorrectResults_WhenGivenSetOfValues(int val1, int val2, double expectedResult) {
        double result = 0.0d;
        try {
            result = simpleCalculator.divide(val1, val2);
        } catch(CalculationException ex) {
            ex.printStackTrace();
        }
        assertEquals(expectedResult, result);
    }

    @ParameterizedTest
    @CsvSource({
            "10, 10, 100",
            "100000, 100000, 1.0E10"
    })
    public void multiply_ShouldReturnCorrectResults_WhenGivenSetOfValues(int val1, int val2, double expectedResult) {
        var result = simpleCalculator.multiply(val1, val2);
        assertEquals(result, expectedResult);
    }
}