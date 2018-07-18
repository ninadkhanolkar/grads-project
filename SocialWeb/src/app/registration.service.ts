import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  constructor(private http:HttpClient) { }

  register(data){
    let url = `http://localhost:8080/api/wiseconnect/v1/employee`;
    return this.http.post(url, data);
  }
}
