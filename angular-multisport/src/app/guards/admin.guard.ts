import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, CanActivateChild, Router, RouterStateSnapshot } from '@angular/router';
import { map, Observable } from 'rxjs';
import { Role } from '../models/user/Role';
import { AdminService } from '../services/admin.service';

@Injectable({
  providedIn: 'root'
})
export class AdminGuard implements CanActivate, CanActivateChild {
  
  constructor(private adminService: AdminService, private router: Router) {}
  
  canActivateChild(childRoute: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> {
    return this.checkAdminAccess()
  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean>  {
      return this.checkAdminAccess()
  }
  

  private checkAdminAccess(): Observable<boolean>  {
    return this.adminService.admin().pipe(
      map((data) => {
       
        if (data == null) {
          this.router.navigate(['/multisport'])
          return false
        } else if (data['role'] != null && data['role'] == Role.ADMIN) {
          this.adminService.setUser(data)
          return true
        }
        this.router.navigate(['/multisport'])
        return false
      })
    )

  }

}
