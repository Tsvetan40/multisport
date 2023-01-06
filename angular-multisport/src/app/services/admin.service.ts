import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
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
}
