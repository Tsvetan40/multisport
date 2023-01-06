import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { User } from '../models/User';

@Injectable({
  providedIn: 'root'
})
export class AdminServiceService {

  private readonly url: string = "http://localhost:8080/multisport/admin"
  private user!: User;
  
  constructor(private http: HttpClient) { }


  admin(): Observable<User> {
    return this.http.get<User>(this.url, {withCredentials: true}).pipe(
      map((data) => {

        if (data['email'] != null && data['email'].includes('@multisport.com')) {
          this.user = data;
          return this.user;
        }
        return this.user
      })
    )
  }

  getUser(): User {
    return this.user;
  }
}
