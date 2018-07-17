import {Component, OnInit, Input} from '@angular/core';
import {EmployeeService} from '../employee.service';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {

  @Input() employees: any;
  employeeCredentials: any = [];

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
    this.employeeService.fetchDetails(url)
    .subscribe((employee)=>{
      console.log(employee);
    });
  }

}
