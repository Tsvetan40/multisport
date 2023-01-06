import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { map, Observable } from 'rxjs';
import { User } from '../models/User';
import { AdminServiceService } from '../services/admin-service.service';

@Injectable({
  providedIn: 'root'
})
export class AdminGuard implements CanActivate {
  
  constructor(private adminService: AdminServiceService) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean>  {
      return this.checkAdminAccess()
  }
  

  private checkAdminAccess(): Observable<boolean>  {
    console.log('hello from guard')
    return this.adminService.admin().pipe(
      map((data) => {
        if (data == null) {
          return false
        }
        if (data['email'] != null && data['email'].includes('@multisport.com')) {
          this.adminService.setUser(data)
          return true
        }
        return false
      })
    )

  }

}
