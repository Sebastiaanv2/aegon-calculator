import { Operator } from 'rxjs';
import { CalculationOperator } from './calculationOperator';

export class Calculation {
    operator: CalculationOperator;
    value1: string;
    value2: string;
    result: string;

    constructor(operator: CalculationOperator, value1: string, value2: string, result: string = '') {
        this.operator = operator;
        this.value1 = value1;
        this.value2 = value2;
        this.result = result;
    }

}
