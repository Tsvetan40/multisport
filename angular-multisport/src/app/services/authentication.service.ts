import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable,  } from 'rxjs';
import { LoggedUser } from '../models/user/LoggedUser';
import { RegisteredUser } from '../models/user/RegisteredUser';
import { User } from '../models/user/User';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  readonly url: string = "http://localhost:8080/multisport"
  
  constructor(private http: HttpClient) { }

  public login(user: LoggedUser): Observable<User> {
    
    const logedUserJSON = {'email': user.email, 'password': user.password }

    return this.http.post<User>(`${this.url}/login`, logedUserJSON, { withCredentials: true });
  }

  public registartion(user: RegisteredUser): Observable<RegisteredUser> {
    const regUserJSON = {'firstName': user.firstName,
      'secondName': user.secondName,
      'age': user.age,
      'email': user.email,
      'password': user.password,
      'status': user.status,
      'role': user.role 
    }

    return this.http.post<RegisteredUser>(`${this.url}/newuser`, regUserJSON, { withCredentials: true })
  }

  public logout(): Observable<RegisteredUser> {
    return this.http.post<RegisteredUser>(`${this.url}/logout`, {}, { withCredentials:true })
  }

  public checkSession(): Observable<User> {
    return this.http.post<User>(this.url, {}, { withCredentials: true })
  }
}
