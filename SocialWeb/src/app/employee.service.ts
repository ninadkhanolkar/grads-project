import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  constructor(private http: HttpClient,private loginService:LoginService) {}

 

  loadEmployee() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'Authorization':  sessionStorage.getItem("token")
      })
    };
    let url = "http://localhost:8080/api/wiseconnect/v1/employees/approved-employees/";
    return this.http.get(url,httpOptions);
  }


  fetchDetails(empId) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'Authorization':  sessionStorage.getItem("token")
      })
    };
    const final = "http://localhost:8080/api/wiseconnect/v1/employees/";
    return this.http.get(final+empId,httpOptions);
  }

  loadPendingListEmployees() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'Authorization':  sessionStorage.getItem("token")
      })
    };
    let url = "http://localhost:8080/api/wiseconnect/v1/admin/pendingEmployees";
    return this.http.get(url,httpOptions);
  }

  acceptEmployeeByAdmin(id) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'Authorization':  sessionStorage.getItem("token")
      })
    };
    let final = `http://localhost:8080/api/wiseconnect/v1/admin/employees/${id}`;
    return this.http.put(final, id,httpOptions);
  }

  rejectEmployeeByAdmin(id) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'Authorization':  sessionStorage.getItem("token")
      })
    };
    let final = `http://localhost:8080/api/wiseconnect/v1/admin/employees/${id}`;
    return this.http.delete(final,httpOptions);
  }

  loadReportees(id){
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'Authorization':  sessionStorage.getItem("token")
      })
    };
    let url = `http://localhost:8080/api/wiseconnect/v1/${id}/reportees`;
    return this.http.get(url,httpOptions);
  }

}
