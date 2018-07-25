import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  isAuthenticated:boolean=false;
  token:any;
  constructor(private http:HttpClient,private router:Router) { }
   
  tryLogin(credential,userRole){
    this.token="";
    this.isAuthenticated=false;
    let url = "http://localhost:8080/auth";
    this.http.post(url,credential).subscribe((e:any)=>{
      console.log(e);
      if(e["token"]){
      this.token=e["token"];
      this.isAuthenticated=true;
      if (userRole === 'Admin') {
          this.router.navigate(['admin/profile', { 'role': userRole, 'username': credential.username }]);
        }
        if (userRole === 'Employee') {
          this.router.navigate(['employee/profile', { 'role': userRole, 'username': credential.username }]);
        }
      
      }
    })
    return true;

  }

  logout(){
    this.isAuthenticated=false;
    this.token="";
  }

}
