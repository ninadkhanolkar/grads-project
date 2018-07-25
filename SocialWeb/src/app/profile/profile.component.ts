import {Component, OnInit, Input} from '@angular/core';
import {EmployeeService} from '../employee.service';
import { Router, ActivatedRoute } from '@angular/router';
import { LoginService } from '../login.service';
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
    private route: ActivatedRoute,
    private loginService:LoginService) {}

  ngOnInit() {
    this.route.params.subscribe(params => console.log(params));
    if(this.route.snapshot.paramMap.get('role')){
      console.log(this.loginService.roles)
      if(this.loginService.roles.indexOf('ROLE_SUPERADMIN')>=0){
       this.role = "Super Admin"
      }
      else{
        this.role = this.route.snapshot.paramMap.get('role');
      }
      
    }
    // if(this.route.snapshot.paramMap.get('username')){
    //   this.empId=this.route.snapshot.paramMap.get('username');
    // }
    this.empId=this.loginService.username;
    
   
  }

  isEmployee(){
    if(this.role === "Employee"){
      return true;
    }
    else 
    return false;
  }
  isAdmin(){
    if(this.role==="Admin"){
      return true;
    }
    else 
      return this.isSuperAdmin() || false;
  }
  isSuperAdmin(){
    if(this.role==="Super Admin"){
      return true;
    }
    else 
      return false;
  }

}
