import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RelaxCenter } from '../models/centers/RelaxCenter';
import { SportCenter } from '../models/centers/SportCenter';
import { Article } from '../models/page/Article';
import { RegisteredUser } from '../models/user/RegisteredUser';
import { Plan } from '../models/Plan';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  private readonly url: string = "http://localhost:8080/multisport/admin"
  private user!: RegisteredUser
  
  constructor(private http: HttpClient) { }


  admin(): Observable<RegisteredUser> {
    return this.http.post<RegisteredUser>(this.url, {}, { withCredentials: true })
  }

  getUser(): RegisteredUser {
    return this.user;
  }

  setUser(user: RegisteredUser): void {
    this.user = user
  }

  saveArticle(article: Article): Observable<Article> {
    return this.http.post<Article>(`${this.url}/articles/newarticle`, article, { withCredentials: true })
  }

  saveSportCenter(sportCenetr: SportCenter): Observable<SportCenter> {
    return this.http.post<SportCenter>(`${this.url}/centers/newcenter`, sportCenetr, { withCredentials: true } )
  }

  saveRelaxCenter(relaxCenter: RelaxCenter): Observable<RelaxCenter> {
    return this.http.post<RelaxCenter>(`${this.url}/centers/newcenter`, relaxCenter, { withCredentials: true })
  }

  savePlan(plan: Plan, picture: File): Observable<Plan> {
    const formParams = new FormData()
    formParams.append('name', plan.getName())
    formParams.append('price', plan.getPrice().toString())
    
    let centersAdddressesString = ''
    plan.getCentersAddresses().forEach(centersAdddress => {
      centersAdddressesString += centersAdddress;
    })


    formParams.append('centersAddresses', centersAdddressesString)
    formParams.append('file', picture)

    return this.http.post<Plan>(`${this.url}/newplan`, formParams, { withCredentials: true })
  }

  getAllArticlesTitle(): Observable<string[]> {
    return this.http.get<string[]>(`${this.url}/articles`, { withCredentials: true })
  }

  deleteArticleByTitile(title: string): Observable<Article> {
    return this.http.delete<Article>(`${this.url}/articles/?title=${title}`, { withCredentials: true })
  }
}
