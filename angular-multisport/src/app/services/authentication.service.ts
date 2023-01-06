import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable,  } from 'rxjs';
import { LoggedUser } from '../models/LoggedUser';
import { User } from '../models/User';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  readonly url: string = "http://localhost:8080/multisport"
  
  constructor(private http: HttpClient) { }

  public login(user: LoggedUser): Observable<LoggedUser> {
    return this.http.post<LoggedUser>(`${this.url}/login`, user, {withCredentials: true});
  }

  public registartion(user: User): Observable<User> {
    debugger
    return this.http.post<User>(`${this.url}/newuser`, user, {withCredentials: true})
  }

  public logout(): Observable<User> {
    return this.http.post<User>(`${this.url}/logout`, {}, { withCredentials:true })
  }

  public checkSession(): Observable<User> {
    return this.http.post<User>(this.url, {}, { withCredentials: true })
  }
}
