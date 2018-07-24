import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  isAuthenticated:boolean=false;
  token:any;
  constructor(private http:HttpClient) { }
   
  getToken(credential){
    let url = "http://localhost:8080/auth";
    this.http.post(url,credential).subscribe((e:any)=>{
      if(e["token"]){
      this.token=e["token"];
      this.isAuthenticated=true;
      }
    })

  }

  logout(){
    this.isAuthenticated=false;
    this.token="";
  }

}
