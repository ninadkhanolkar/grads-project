import {Component, OnInit, Input} from '@angular/core';
import {EmployeeService} from '../employee.service';
import { Router, ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  employeeUrl = "/api/wiseconnect/v1/employee/WT299";
  employeeCredentials: any = [];
  role: String;
  constructor(private employeeService: EmployeeService,
    private route: ActivatedRoute) {}

  ngOnInit() {
    this.route.params.subscribe(params => console.log(params));
    this.role = this.route.snapshot.paramMap.get('role');
    console.log(this.role);
  }

  isEmployee(){
    if(this.role === "Employee"){
      return true;
    }
    else
    return false;
  }

}
