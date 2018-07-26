import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http:HttpClient) { }

  getAdmins(){
    let url="http://localhost:8080/api/wiseconnect/v1/admins";
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'Authorization':  sessionStorage.getItem("token")
      })
    };
    this.http.get(url,httpOptions)

  }
}
