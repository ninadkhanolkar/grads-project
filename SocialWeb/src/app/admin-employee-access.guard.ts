import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminEmployeeAccessGuard implements CanActivate {
  constructor(private router:Router){}
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if (!sessionStorage.getItem('token')) {
      this.router.navigateByUrl("/")
      return false;
    }
    if (state.url.startsWith('/admin')) {
      if (sessionStorage.getItem('roles').indexOf('ROLE_ADMIN')>=0)
        return true;
    }
    else if (state.url.startsWith('/employee')) {
      if (sessionStorage.getItem('roles').indexOf('ROLE_USER')>=0)
        return true;
    }
    else{
      this.router.navigateByUrl("")
      return false;
    } 
  }
}
