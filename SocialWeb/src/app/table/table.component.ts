import {Component, OnInit, Input} from '@angular/core';
import {EmployeeService} from '../employee.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {

  @Input() employees: any;
  employeeCredentials: any = [];
  router: Router;

  constructor(private employeeService: EmployeeService) {}

  ngOnInit() {
    this.getCredentials();
  }

  getCredentials() {
    this.employeeService.loadEmployee()
      .subscribe((credentials) => {
        this.employeeCredentials = credentials;
      });

  }

  viewProfile(url) {
    console.log(url);
    this.employeeService.fetchDetails(url)
    .subscribe((employee)=>{
      //this.router.navigate()
    });
  }

}
