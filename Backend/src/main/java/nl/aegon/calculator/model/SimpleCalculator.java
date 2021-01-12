package nl.aegon.calculator.model;

import nl.aegon.calculator.exceptions.CalculationException;
import org.springframework.stereotype.Component;

@Component
public class SimpleCalculator {
    public double add(int value1, int value2) {
        return (double) value1 + value2;
    }

    public double subtract(int value1, int value2) {
        return (double) value1 - value2;
    }

    public double divide(int value1, int value2) throws CalculationException {
        var result = (double) value1 / value2;
        if (result == Double.POSITIVE_INFINITY || result == Double.NEGATIVE_INFINITY)
            throw new CalculationException("Infinity");
        return result;
    }

    public double multiply(int value1, int value2) {
        return (double) value1 * value2;
    }
}
