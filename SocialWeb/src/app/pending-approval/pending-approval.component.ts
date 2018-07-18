import {Component, OnInit} from '@angular/core';
import {EmployeeService} from '../employee.service';
import {Router} from '@angular/router';


@Component({
  selector: 'app-pending-approval',
  templateUrl: './pending-approval.component.html',
  styleUrls: ['./pending-approval.component.css']
})
export class PendingApprovalComponent implements OnInit {



  constructor(private employeeService: EmployeeService,
    private router: Router) {}
  PendingEmployeeDetails: any = [];
  ngOnInit() {
    this.getPendingEmployeeDetails();
  }

  getPendingEmployeeDetails() {
    this.employeeService.loadPendingListEmployees()
      .subscribe((credentials) => {

        this.PendingEmployeeDetails = credentials;
      });
  }

  acceptEmployee(url) {
    this.employeeService.acceptEmployeeByAdmin(url)
      .subscribe((credentials) => {

        //        this.PendingEmployeeDetails = credentials;
        this.ngOnInit();
        this.router.navigateByUrl('/profile/pending-approval');

      });
  }

  rejectEmployee(url) {
    this.employeeService.rejectEmployeeByAdmin(url)
      .subscribe((credentials) => {

        //        this.PendingEmployeeDetails = credentials;
      });
  }
  viewProfile(url) {
    this.employeeService.fetchDetails(url)
      .subscribe((employee) => {
        console.log(employee);
      });
  }

}
