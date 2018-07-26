import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  isAuthenticated:boolean=false;
  token:any;
  roles:any=[]
  username:any;
  requestedRole:any;
  constructor(private http:HttpClient,private router:Router) { }
   
  tryLogin(credential,userRole){
    this.clearValues();
    let url = "http://localhost:8080/auth";
    this.http.post(url,credential).subscribe((e:any)=>{
      console.log(e);
      if(e["token"]){
      sessionStorage.setItem("username",credential.username)
      sessionStorage.setItem("token",e["token"])
      sessionStorage.setItem("roles",e["authorities"])
      sessionStorage.setItem("requestedRole",userRole)
      sessionStorage.setItem("isAuthenticated","true")
        if (userRole === 'Admin' && this.roles.indexOf('ROLE_ADMIN')>=0) {
          this.router.navigate(['admin/profile']);
        }
        else if (userRole === 'Employee' && this.roles.indexOf('ROLE_USER')>=0) {
          this.router.navigate(['employee/profile']);
        }
        else{
             this.clearValues();
        }
      
      }
    })
    
    return true;

  }

  logout(){
    this.clearValues();
  }

  clearValues(){
    this.username="";
    this.token="";
    this.isAuthenticated=false;
    this.roles="";
  }

}
