import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable,  } from 'rxjs';
import { LoggedUser } from '../models/user/LoggedUser';
import { RegisteredUser } from '../models/user/RegisteredUser';
import { User } from '../models/user/User';
import { HttpHeaders } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  readonly url: string = "http://localhost:8080/multisport"
  private email: string
  private password: string
  private isAdmin: boolean 

  constructor(private http: HttpClient) { 
    this.email = ''
    this.password = ''
    this.isAdmin = false
  }

  public login(user: LoggedUser): Observable<User> {
    
    this.email = user.email
    this.password = user.password

    const credetentials = this.email + ':' + this.password

    const authHeader = new HttpHeaders({
      'Authorization': 'Basic ' + window.btoa(credetentials)
    })

    const options = { headers: authHeader, withCredentials: true }
    return this.http.post<User>(`${this.url}/login`, { }, options);
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

    this.email = user.email
    this.password = user.password

    return this.http.post<RegisteredUser>(`${this.url}/newuser`, regUserJSON, { withCredentials: true })
  }

  public logout() {
    this.email = ''
    this.password = ''
    this.isAdmin = false
  }

  public setIsAdmin(isAdmin: boolean) {
    this.isAdmin = isAdmin
  }

  public checkCredetentials(): boolean {
    return this.isAdmin
  }
}
