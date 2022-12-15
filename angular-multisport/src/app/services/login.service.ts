import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs';
import { User } from '../models/User';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  readonly url: string = "http://localhost:8080/multisport"
  
  constructor(private http: HttpClient) { }

  public login(email: string, password: string): Observable<User> {
    const body = JSON.stringify({ email, password })
    console.log('body= ' + body)
    return this.http.post<User>(`${this.url}/login`, body);
  }
}
