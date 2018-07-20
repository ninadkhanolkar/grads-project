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
  role: String;
  empId:String;
  constructor(private employeeService: EmployeeService,
    private route: ActivatedRoute) {}

  ngOnInit() {
    this.route.params.subscribe(params => console.log(params));
    if(this.route.snapshot.paramMap.get('role')){
      this.role = this.route.snapshot.paramMap.get('role');
    }
    if(this.route.snapshot.paramMap.get('username')){
      this.empId=this.route.snapshot.paramMap.get('username');
    }
    
    console.log(this.role);
  }

  isEmployee(){
    console.log(this.role)
    if(this.role === "Employee"){
      return true;
    }
    else 
    return false;
  }
  isAdmin(){
    console.log(this.role)
    if(this.role==="Admin"){
      return true;
    }
    else 
      return false;
  }

}
