import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  isAuthenticated: boolean = false;
  token: any;
  roles: any = []
  username: any;
  requestedRole: any;
  isInvalid:any
  constructor(private http: HttpClient, private router: Router) { }

  tryLogin(credential, userRole) {
    this.clearValues();
    let url = "http://localhost:8080/auth";
    this.http.post(url, credential).subscribe((e: any) => {
      // console.log(e);
      if (e["token"]) {
        this.roles = e["authorities"]
        sessionStorage.setItem("username", credential.username)
        sessionStorage.setItem("token", e["token"])
        sessionStorage.setItem("roles", e["authorities"])
        sessionStorage.setItem("requestedRole", userRole)
        sessionStorage.setItem("isAuthenticated", "true")
        this.isInvalid=false;
        if (sessionStorage.getItem("requestedRole") === 'Admin' && sessionStorage.getItem("roles").indexOf('ROLE_ADMIN') >= 0) {
          this.router.navigate(['admin/profile']);
        }
        else if (sessionStorage.getItem("requestedRole") === 'Employee' && sessionStorage.getItem("roles").indexOf('ROLE_USER') >= 0 && sessionStorage.getItem("roles").indexOf('ROLE_ADMIN') < 0) {
          this.router.navigate(['employee/profile/profile-info']);
        }
        else {
          this.clearValues();
          this.router.navigate([''])
          sessionStorage.setItem("invalidCredentials", "true")
          this.isInvalid=true;
        }

      }
    },
      (error) => {
        this.clearValues();
        this.isInvalid=true;
        this.router.navigate([''])
        sessionStorage.setItem("invalidCredentials", "true")
        
      }
    )

    //return (sessionStorage.getItem("isAuthenticated")==="true");    

  }

  logout() {
    this.clearValues();
  }

  clearValues() {
    this.username = "";
    this.token = "";
    this.isAuthenticated = false;
    this.roles = "";
    sessionStorage.clear();
  }

}
