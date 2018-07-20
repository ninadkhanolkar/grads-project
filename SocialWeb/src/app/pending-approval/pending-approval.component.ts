import {Component, OnInit} from '@angular/core';
import {EmployeeService} from '../employee.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-pending-approval',
  templateUrl: './pending-approval.component.html',
  styleUrls: ['./pending-approval.component.css']
})
export class PendingApprovalComponent implements OnInit {

  constructor(private employeeService: EmployeeService,
    private router: Router,
    private route:ActivatedRoute) {} //This parameter is used to enable router.navigate for relative routes
  pendingEmployeeDetails: any = [];
  ngOnInit() {
    this.getPendingEmployeeDetails();
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
        this.ngOnInit();
      });
  }

  rejectEmployee(url) {
    this.employeeService.rejectEmployeeByAdmin(url)
      .subscribe((credentials) => {
        this.ngOnInit();
      });
  }


  viewProfile(empId) {
    this.employeeService.fetchDetails(empId)
      .subscribe((employee) => {
        console.log(employee);
      });
      this.router.navigate(['profile-info',{p1:empId}],{ relativeTo: this.route })
      
  }
}
