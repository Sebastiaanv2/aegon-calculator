import { CalculationOperator } from '../models/calculationOperator';

export class Utilities {
    static displayCalcOperator(operator: CalculationOperator): string {
        if (operator === undefined) return '';
        switch (operator) {
            case CalculationOperator.Add || 0:
                return '+';
            case CalculationOperator.Subtract || 1:
                return '-';
            case CalculationOperator.Divide || 2:
                return '/';
            case CalculationOperator.Multiply || 3:
                return '*';
        }
    }
}
