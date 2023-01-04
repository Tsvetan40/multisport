import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable,  } from 'rxjs';
import { LoggedUser } from '../models/LoggedUser';
import { User } from '../models/User';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  readonly url: string = "http://localhost:8080/multisport"
  
  constructor(private http: HttpClient) { }

  public login(user: LoggedUser): Observable<LoggedUser> {
    return this.http.post<LoggedUser>(`${this.url}/login`, user, {withCredentials: true});
  }

  public registartion(user: User): Observable<User> {
    return this.http.post<User>(`${this.url}/newuser`, user, {withCredentials: true})
  }

  public manageUsers(): Observable<User> {
    return this.http.get<User>(`${this.url}/admin`, {withCredentials: true})
  }
}
