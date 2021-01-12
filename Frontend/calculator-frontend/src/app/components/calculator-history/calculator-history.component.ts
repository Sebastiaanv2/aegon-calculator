import { Component, Input, OnInit } from '@angular/core';
import { Calculation } from 'src/app/models/calculation';
import { CalculationOperator } from 'src/app/models/calculationOperator';
import { CalculationService } from 'src/app/services/calculation.service';
import { Utilities } from 'src/app/shared/utilities';

@Component({
  selector: 'app-calculator-history',
  templateUrl: './calculator-history.component.html',
  styleUrls: ['./calculator-history.component.scss']
})
export class CalculatorHistoryComponent implements OnInit {

  @Input()
  calculationHistory: Array<Calculation> = [];

  constructor() { }

  ngOnInit(): void {
  }

  public displayCalculation(calculation: Calculation): string {
    const enumValue = (CalculationOperator as any)[calculation.operator];
    return `${calculation.value1} ${Utilities.displayCalcOperator(enumValue)} ${calculation.value2} = ${calculation.result}`;
  }

}
