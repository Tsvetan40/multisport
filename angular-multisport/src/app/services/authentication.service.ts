import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable,  } from 'rxjs';
import { LoggedUser } from '../models/user/LoggedUser';
import { RegisteredUser } from '../models/user/RegisteredUser';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  readonly url: string = "http://localhost:8080/multisport"
  
  constructor(private http: HttpClient) { }

  public login(user: LoggedUser): Observable<LoggedUser> {
    
    const logedUserJSON = {'email': user.email, 'password': user.password }

    return this.http.post<LoggedUser>(`${this.url}/login`, logedUserJSON, {withCredentials: true});
  }

  public registartion(user: RegisteredUser): Observable<RegisteredUser> {
    
    return this.http.post<RegisteredUser>(`${this.url}/newuser`, user, {withCredentials: true})
  }

  public logout(): Observable<RegisteredUser> {
    return this.http.post<RegisteredUser>(`${this.url}/logout`, {}, { withCredentials:true })
  }

  public checkSession(): Observable<RegisteredUser> {
    return this.http.post<RegisteredUser>(this.url, {}, { withCredentials: true })
  }
}
