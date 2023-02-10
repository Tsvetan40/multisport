import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RelaxCenter } from '../models/centers/RelaxCenter';
import { SportCenter } from '../models/centers/SportCenter';
import { Article } from '../models/page/Article';
import { RegisteredUser } from '../models/user/RegisteredUser';
import { Plan } from '../models/Plan';
import { User } from '../models/user/User';

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

  saveArticle(article: Article, picture: File): Observable<Article> {
    const formAttributes = new FormData();
    formAttributes.append('title', article.title)
    formAttributes.append('content', article.content)
    formAttributes.append('picture', picture)

    return this.http.post<Article>(`${this.url}/articles/newarticle`, formAttributes, { withCredentials: true })
  }

  saveSportCenter(sportCenetr: Object): Observable<SportCenter> {
    return this.http.post<SportCenter>(`${this.url}/centers/newcenter`, sportCenetr, { withCredentials: true } )
  }

  saveRelaxCenter(relaxCenter: Object): Observable<RelaxCenter> {
    return this.http.post<RelaxCenter>(`${this.url}/centers/newcenter`, relaxCenter, { withCredentials: true })
  }

  savePlan(plan: Plan, picture: File): Observable<Plan> {
    const formParams = new FormData()
    formParams.append('name', plan.name)
    formParams.append('price', plan.price.toString())
    
    let centersAdddressesString = ''
    plan.centersAddresses.forEach(centersAdddress => {
      centersAdddressesString += centersAdddress;
    })

    formParams.append('centersAddresses', centersAdddressesString)
    formParams.append('file', picture)

    return this.http.post<Plan>(`${this.url}/newplan`, formParams, { withCredentials: true })
  }

  getAllArticlesTitle(): Observable<Article[]> {
    return this.http.get<Article[]>(`${this.url}/articles`, { withCredentials: true })
  }

  deleteArticleByTitile(title: string): Observable<any> {
    return this.http.delete<any>(`${this.url}/articles/?title=${title}`, { withCredentials: true })
  }

  getUserById(id: number): Observable<User> {
    return this.http.get<User>(`${this.url}/users/${id}`, { withCredentials: true })
  }

  blockUser(id: number): Observable<User> {
    return this.http.post<User>(`${this.url}/users/blocking/${id}`, { }, { withCredentials: true })
  }

  restoreUserRights(id: number): Observable<User> {
    return this.http.post<User>(`${this.url}/users/unblocking/${id}`, { }, { withCredentials: true })
  }
}
