import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Calculation } from '../models/calculation';
import { environment } from './../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CalculationService {

  constructor(private http: HttpClient) { }

  public async calculate(calculation: Calculation): Promise<Calculation> {
    const result = await this.http.post<Calculation>(`${environment.backendUrl}/calculation`, calculation).toPromise();
    return result;
  }

  public async getCalculationHistory(): Promise<Array<Calculation>> {
    const result = await this.http.get<Array<Calculation>>(`${environment.backendUrl}/calculation`).toPromise();
    return result;
  }


}
