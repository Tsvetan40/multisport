import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RelaxCenter } from '../models/centers/RelaxCenter';
import { SportCenter } from '../models/centers/SportCenter';
import { Article } from '../models/page/Article';
import { RegisteredUser } from '../models/user/RegisteredUser';
import { Plan } from '../models/Plan';
import { User } from '../models/user/User';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  private readonly url: string = "http://localhost:8080/multisport/admin"
  private user!: RegisteredUser
  
  constructor(private http: HttpClient, private authService: AuthenticationService) { }


  getUser(): RegisteredUser {
    return this.user;
  }

  setUser(user: RegisteredUser): void {
    this.user = user
  }

  admin(): Observable<RegisteredUser> {
    return this.http.post<RegisteredUser>(this.url, {}, this.authService.createOptions())
  }

  saveArticle(article: Article, picture: File): Observable<Article> {
    const formAttributes = new FormData();
    formAttributes.append('title', article.title)
    formAttributes.append('content', article.content)
    formAttributes.append('picture', picture)

    return this.http.post<Article>(`${this.url}/articles/newarticle`, formAttributes, this.authService.createOptions())
  }

  saveSportCenter(sportCenetr: Object): Observable<SportCenter> {
    return this.http.post<SportCenter>(`${this.url}/centers/newcenter`, sportCenetr, this.authService.createOptions())
  }

  saveRelaxCenter(relaxCenter: Object): Observable<RelaxCenter> {
    return this.http.post<RelaxCenter>(`${this.url}/centers/newcenter`, relaxCenter, this.authService.createOptions())
  }

  savePlan(plan: Plan, picture: File): Observable<Plan> {
    const formParams = new FormData()
    formParams.append('name', plan.name)
    formParams.append('price', plan.price.toString())
    
    let centersAddressesString = ''
    plan.centersAddresses.forEach(centersAdddress => {
      centersAddressesString += centersAdddress + ';';
    })

    formParams.append('centersAddresses', centersAddressesString)
    formParams.append('file', picture)

    return this.http.post<Plan>(`${this.url}/newplan`, formParams, this.authService.createOptions())
  }

  getAllArticlesTitle(): Observable<Article[]> {
    return this.http.get<Article[]>(`${this.url}/articles`, this.authService.createOptions())
  }

  deleteArticleByTitile(title: string): Observable<any> {
    return this.http.delete<any>(`${this.url}/articles/?title=${title}`, this.authService.createOptions())
  }

  getUserById(id: number): Observable<User> {
    return this.http.get<User>(`${this.url}/users/${id}`, this.authService.createOptions())
  }

  blockUser(id: number): Observable<User> {
    return this.http.post<User>(`${this.url}/users/blocking/${id}`, { }, this.authService.createOptions())
  }

  restoreUserRights(id: number): Observable<User> {
    return this.http.post<User>(`${this.url}/users/unblocking/${id}`, { }, this.authService.createOptions())
  }

  addAdmin(admin: RegisteredUser): Observable<RegisteredUser> {
    const user = {
      'email': admin.email,
      'password': admin.password,
      'firstName': admin.firstName,
      'secondName': admin.secondName,
      'age': admin.age,
      'status': admin.status,
      'role': admin.role
    }
    return this.http.post<RegisteredUser>(`${this.url}/users/newadmin`, user, this.authService.createOptions())
  }
}
