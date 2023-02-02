import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SportCenter } from '../models/centers/SportCenter';
import { Article } from '../models/page/Article';
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

  public getSingleArticle(title: string): Observable<Article> {
    return this.http.get<Article>(`${this.url}/articles/${title}`, { withCredentials: true })
  }

  public getAllArticle(): Observable<Article[]> {
    return this.http.get<Article[]>(`${this.url}/articles`, { withCredentials: true })
  }

  public getSinglePlan(name: string): Observable<Plan> {
    return this.http.get<Plan>(`${this.url}/plans/${name}`, {withCredentials: true} )
  }

  public getAllSportCenters(): Observable<SportCenter[]> {
    return this.http.get<SportCenter[]>(`${this.url}/sport-centers`, { withCredentials: true })
  }
}
