import { Component, OnInit, NgZone } from '@angular/core';
import { EmployeeService } from '../employee.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-pending-approval',
  templateUrl: './pending-approval.component.html',
  styleUrls: ['./pending-approval.component.css']
})
export class PendingApprovalComponent implements OnInit {

  constructor(private employeeService: EmployeeService, private router: Router) { }
  pendingEmployeeDetails: any = [];

  ngOnInit() {
    this.getPendingEmployeeDetails();
  }

  ngDoCheck() {
  }

  getPendingEmployeeDetails() {
    this.employeeService.loadPendingListEmployees()
      .subscribe((credentials) => {
        this.pendingEmployeeDetails = credentials;
      });
  }

  acceptEmployee(id) {
    this.employeeService.acceptEmployeeByAdmin(id)
      .subscribe((credentials) => {
        console.log(credentials)
        this.ngOnInit();
      });
  }

  rejectEmployee(id) {
    this.employeeService.rejectEmployeeByAdmin(id)
      .subscribe((credentials) => {
        this.ngOnInit();
      });
  }

  viewProfile(url) {
    this.employeeService.fetchDetails(url)
      .subscribe((employee) => {
        console.log(employee);
      });
  }
}
