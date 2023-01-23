import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Article } from '../models/page/Article';
import { RegisteredUser } from '../models/user/RegisteredUser';

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

  getAllArticlesTitle(): Observable<string[]> {
    return this.http.get<string[]>(`${this.url}/articles`, { withCredentials: true })
  }

  deleteArticleByTitile(title: string): Observable<Article> {
    return this.http.delete<Article>(`${this.url}/articles/?title=${title}`, { withCredentials: true })
  }
}
