package nl.aegon.calculator.service;

import nl.aegon.calculator.exceptions.CalculationException;
import nl.aegon.calculator.model.Calculation;
import nl.aegon.calculator.model.SimpleCalculator;
import nl.aegon.calculator.repository.CalculatorRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CalculatorService {
    private final CalculatorRepository calculatorRepository;
    private final SimpleCalculator simpleCalculator;

    public CalculatorService(CalculatorRepository calculatorRepository, SimpleCalculator simpleCalculator) {
        this.calculatorRepository = calculatorRepository;
        this.simpleCalculator = simpleCalculator;
    }

    @Transactional
    public Calculation calculate(Calculation calculation) throws CalculationException {
        var result = 0.0d;
        switch (calculation.getOperator()) {
            case Add:
                result = simpleCalculator.add(calculation.getValue1(), calculation.getValue2());
                break;
            case Subtract:
                result = simpleCalculator.subtract(calculation.getValue1(), calculation.getValue2());
                break;
            case Divide:
                result = simpleCalculator.divide(calculation.getValue1(), calculation.getValue2());
                break;
            case Multiply:
                result = simpleCalculator.multiply(calculation.getValue1(), calculation.getValue2());
                break;
        }
        calculation.setResult(result);
        calculatorRepository.save(calculation);
        return calculation;
    }

    @Transactional
    public List<Calculation> getCalculationHistory() {
        List<Calculation> calculations = new ArrayList<>();
        calculatorRepository.findAll().forEach(calculations::add);
        return calculations;
    }
}
