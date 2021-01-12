import { Component, OnInit } from '@angular/core';
import { NumberValueAccessor } from '@angular/forms';
import { Calculation } from 'src/app/models/calculation';
import { CalculationOperator } from 'src/app/models/calculationOperator';
import { CalculationService } from 'src/app/services/calculation.service';
import { Utilities } from 'src/app/shared/utilities';
import { isUndefined } from 'util';

@Component({
  selector: 'app-calculator',
  templateUrl: './calculator.component.html',
  styleUrls: ['./calculator.component.scss']
})
export class CalculatorComponent implements OnInit {

  val1 = '0';
  val2: string;
  currentOperator: CalculationOperator;
  display: string;
  result: string;
  calculationHistory: Calculation[] = [];

  calcOp = CalculationOperator;

  constructor(private readonly calculatorService: CalculationService) { }

  ngOnInit(): void {
    this._updateDisplay();
    this.calculatorService.getCalculationHistory().then(res => {
      this.calculationHistory = res;
    });
  }

  public onAddNumber(num: string): void {
    if (this.currentOperator === undefined) {
      if (this.val1 === '0') {
        this.val1 = num;
      } else {
        this.val1 += num;
      }
    } else {
      if (this.val2 === undefined || this.val2 === '0') {
        this.val2 = num;
      } else {
        this.val2 += num;
      }
    }
    this._updateDisplay();
  }

  public onSetOperator(operator: CalculationOperator): void {
    this.currentOperator = operator;
    this._updateDisplay();
  }

  public _updateDisplay(): void {
    this.display = `${this.val1} ${Utilities.displayCalcOperator(this.currentOperator)} ${this.val2 === undefined ? '' : this.val2}`;
  }

  public onDelete(): void {
    if (this.currentOperator === undefined) {
      this.val1 = this._removeLastCharachter(this.val1);
      if (this.val1 === '') { this.val1 = '0'; }
    } else {
      if (this.val2 === undefined) {
        this.currentOperator = undefined;
      } else {
        this.val2 = this._removeLastCharachter(this.val2);
        if (this.val2 === '') this.val2 = undefined;
      }
    }
    this._updateDisplay();
  }

  private _removeLastCharachter(str: string): string {
    return str.substring(0, str.length - 1);
  }

  private _resetCalc(): void {
    this.val1 = '0';
    this.currentOperator = undefined;
    this.val2 = undefined;
    this._updateDisplay();
  }

  public onEquals(): void {
    if (this.currentOperator === CalculationOperator.Divide && this.val2 === '0') {
      this.result = 'Division by zero not possible.';
      return;
    }
    if (this.currentOperator === undefined || this.val2 === undefined) {
      this.result = 'Incomplete equation.';
      return;
    }
    this.calculatorService.calculate(new Calculation(this.currentOperator, this.val1, this.val2)).then(res => {
      const enumValue = (CalculationOperator as any)[res.operator];
      this.result = `${res.value1} ${Utilities.displayCalcOperator(enumValue)} ${res.value2} = ${res.result}`;
      this._resetCalc();
    });
  }

}
