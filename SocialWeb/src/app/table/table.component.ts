import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import { EmployeeService } from '../employee.service';
import { Router, ActivatedRoute } from '@angular/router';
import { LoginService } from '../login.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit,OnDestroy {

  id: String;
  employeeCredentials: any = [];
  type: String;
  employees:{firstName:string,lastName:string,id:string}[];
  filteredEmployees:any=[];
  subscription:Subscription;

  constructor(private employeeService: EmployeeService,
    private router: Router,
    private route: ActivatedRoute,
    private loginService: LoginService) {
  //  this.subscription=this.employeeService.loadReportees(this.id).subscribe(employees =>this.filteredEmployees= this.employees = employees);
  }


  filter(query: string) {
    this.filteredEmployees=(query) ?
    this.employeeCredentials.filter(e=>e.firstName.toLowerCase().includes(query.toLowerCase())):
    this.employeeCredentials;
  }

  ngOnDestroy(){
   this.subscription.unsubscribe();
  }
  
  ngOnInit() {
    this.type = this.route.snapshot.paramMap.get('type');
    if (this.type === 'employees') {
      this.getCredentials();
    }
    else if (this.type === 'reportees') {
      this.id = sessionStorage.getItem("username");
      console.log(this.id)
      this.getReportees(this.id);
    }

  }

  getCredentials() {
    this.subscription=this.employeeService.loadEmployee()
      .subscribe((credentials) => {
        this.filteredEmployees =this.employeeCredentials= credentials;
      });

  }



  getReportees(id) {
   this.subscription= this.employeeService.loadReportees(id).subscribe((credentials) => {
     this.filteredEmployees= this.employeeCredentials = credentials;
     console.log(this.employeeCredentials);
    })
  }

}
