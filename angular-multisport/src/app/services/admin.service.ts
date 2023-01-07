import { JsonPipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Article } from '../models/page/Article';
import { User } from '../models/User';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  private readonly url: string = "http://localhost:8080/multisport/admin"
  private user!: User
  
  constructor(private http: HttpClient) { }


  admin(): Observable<User> {
    return this.http.post<User>(this.url, {}, { withCredentials: true })
  }

  getUser(): User {
    return this.user;
  }

  setUser(user: User): void {
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
