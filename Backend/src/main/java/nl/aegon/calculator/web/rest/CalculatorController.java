package nl.aegon.calculator.web.rest;

import lombok.extern.slf4j.Slf4j;
import nl.aegon.calculator.exceptions.CalculationException;
import nl.aegon.calculator.model.Calculation;
import nl.aegon.calculator.model.Operator;
import nl.aegon.calculator.service.CalculatorService;
import nl.aegon.calculator.web.rest.dto.CalculationDto;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/calculation")
@Slf4j
public class CalculatorController {
    private final CalculatorService calculatorService;

    ModelMapper modelMapper = new ModelMapper();

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<CalculationDto>> getCalculationHistory() {
        var result = calculatorService.getCalculationHistory();
        if (result == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        var dtoResult = result.stream().map(res -> modelMapper.map(res, CalculationDto.class)).collect(Collectors.toList());
        return ResponseEntity.ok(dtoResult);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CalculationDto> calculate(@RequestBody @Valid CalculationDto calculationDto) {
        Calculation result = null;

        try {
            result = calculatorService.calculate(modelMapper.map(calculationDto, Calculation.class));
        } catch (CalculationException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (result == null) return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        var dtoResult = modelMapper.map(result, CalculationDto.class);

        return ResponseEntity.ok(dtoResult);
    }
}
