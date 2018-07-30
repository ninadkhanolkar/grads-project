import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AllSkillsService {

  constructor(private http:HttpClient) {}

  getSkills(){
    let url = `http://localhost:8080/api/wiseconnect/v1/allskills`;
    return this.http.get(url);
  }
}
