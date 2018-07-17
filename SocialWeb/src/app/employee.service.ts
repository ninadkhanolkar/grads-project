import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  constructor(private http: HttpClient) {}

  loadEmployee() {
    let url = "http://localhost:8080/api/wiseconnect/v1/employee";
    return this.http.get(url);
  }

  fetchDetails(url) {
    return this.http.get(url);
  }
}
