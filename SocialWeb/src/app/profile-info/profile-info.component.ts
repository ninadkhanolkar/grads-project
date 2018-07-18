import {Component, OnInit} from '@angular/core';
import {EmployeeService} from '../employee.service';
import {Router, ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-profile-info',
  templateUrl: './profile-info.component.html',
  styleUrls: ['./profile-info.component.css']
})
export class ProfileInfoComponent implements OnInit {

  constructor(private employeeService: EmployeeService,  private route: ActivatedRoute) {}
  employeeInfo: any;
  ngOnInit() {
//    this.employeeInfo = btoa(this.route.snapshot.params['p1']);
//    console.log(this.employeeInfo);
   
  }
}
