package nl.aegon.calculator.repository;

import nl.aegon.calculator.model.Calculation;
import org.springframework.data.repository.CrudRepository;

public interface CalculatorRepository extends CrudRepository<Calculation, Long> {
}
