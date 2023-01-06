import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { map, Observable } from 'rxjs';
import { AdminServiceService } from '../services/admin-service.service';

@Injectable({
  providedIn: 'root'
})
export class AdminGuard implements CanActivate {
  
  constructor(private adminService: AdminServiceService,private router: Router) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean>  {
      return this.checkAdminAccess()
  }
  

  private checkAdminAccess(): Observable<boolean>  {
    return this.adminService.admin().pipe(
      map((data) => {
        console.log("Can activate")
        if (data['email'] != null && data['email'].includes('@multisport.com')) {
          return true
        }
        return false
      })
    )


  }
}
