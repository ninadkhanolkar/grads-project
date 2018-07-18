import {Component, OnInit} from '@angular/core';
import {EmployeeService} from '../employee.service';
import {Router, ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-profile-info',
  templateUrl: './profile-info.component.html',
  styleUrls: ['./profile-info.component.css']
})
export class ProfileInfoComponent implements OnInit {

  constructor(private employeeService: EmployeeService, private route: ActivatedRoute) {}
  employeeInfo: any;
  id:any;
  name:any;

  ngOnInit() {
    const url = this.route.snapshot.paramMap.get('p1');
    console.log(url);
    this.getEmp(url);
  }
  
  getEmp(url){
    this.employeeService.fetchDetails(url)
      .subscribe((employee) => {
        this.employeeInfo = employee;
        console.log(employee);
        this.id=this.employeeInfo.empId;
      });
  }
}
