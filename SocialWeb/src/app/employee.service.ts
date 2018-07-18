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


  fetchDetails(url) {
    console.log("i am here");
    console.log(url);
    let final = "http://localhost:8080" + url;
    console.log(final);
    return this.http.get(final);
  }

  loadPendingListEmployees() {
    let url = "http://localhost:8080/api/wiseconnect/v1/pendingEmployees";
    return this.http.get(url);
  }

  acceptEmployeeByAdmin(url) {
    let final = "http://localhost:8080" + url + "/accept";
    return this.http.put(final, null);
  }

  rejectEmployeeByAdmin(url) {
    let final = "http://localhost:8080" + url + "/reject";
    return this.http.delete(final, null);
  }

}