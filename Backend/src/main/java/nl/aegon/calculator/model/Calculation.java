package nl.aegon.calculator.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Setter
@Getter
public class Calculation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Operator operator;
    private int value1;
    private int value2;
    private double result;

    public Calculation(Long id, Operator operator, int value1, int value2, double result) {
        this.id = id;
        this.operator = operator;
        this.value1 = value1;
        this.value2 = value2;
        this.result = result;
    }

    public Calculation() {
    }
}
