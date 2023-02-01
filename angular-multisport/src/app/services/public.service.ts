import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Plan } from '../models/Plan';

@Injectable({
  providedIn: 'root'
})
export class PublicService {
  private readonly url: string = "http://localhost:8080/multisport"

  constructor(private http: HttpClient) { }

  public getAllPlans(): Observable<Plan[]> {
    return this.http.get<Plan[]>(`${this.url}/plans`, { withCredentials: true })
  }

  public getSinglePlan(name: string): Observable<Plan> {
    return this.http.get<Plan>(`${this.url}/plans/${name}`, {withCredentials: true} )
  }
}
