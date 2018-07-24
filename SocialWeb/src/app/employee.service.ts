import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  constructor(private http: HttpClient) {}

  loadEmployee() {
    let url = "http://localhost:8080/api/wiseconnect/v1/employeeResponse";
    return this.http.get(url);
  }


  fetchDetails(empId) {
    const final = "http://localhost:8080/api/wiseconnect/v1/employees/";
    console.log(final);
    return this.http.get(final+empId);
  }

  loadPendingListEmployees() {
    let url = "http://localhost:8080/api/wiseconnect/v1/pendingEmployees";
    return this.http.get(url);
  }

  acceptEmployeeByAdmin(id) {
    let final = `http://localhost:8080/api/wiseconnect/v1/employees/${id}`;
    return this.http.put(final, id);
  }

  rejectEmployeeByAdmin(id) {
    let final = `http://localhost:8080/api/wiseconnect/v1/employees/${id}`;
    return this.http.delete(final);
  }

  loadReportees(id){
    let url = `http://localhost:8080/api/wiseconnect/v1/${id}/reportees`;
    return this.http.get(url);
  }

}
