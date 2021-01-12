package nl.aegon.calculator.web.rest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import nl.aegon.calculator.model.Operator;

@Setter
@Getter
public class CalculationDto {
    private Operator operator;
    private int value1;
    private int value2;
    private double result;

    public CalculationDto(Operator operator, int value1, int value2, double result) {
        this.operator = operator;
        this.value1 = value1;
        this.value2 = value2;
        this.result = result;
    }

    public CalculationDto() {
    }
}
