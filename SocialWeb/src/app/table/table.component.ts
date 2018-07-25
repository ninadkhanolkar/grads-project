import {Component, OnInit, Input} from '@angular/core';
import {EmployeeService} from '../employee.service';
import {Router, ActivatedRoute} from '@angular/router';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {

  id:String;
  employeeCredentials: any = [];
  type:String;

  constructor(private employeeService: EmployeeService,
              private router: Router,
              private route:ActivatedRoute,
              private loginService:LoginService) {}

  ngOnInit() {
    this.type=this.route.snapshot.paramMap.get('type');
    if(this.type ==='employees'){
      this.getCredentials();
    }
    else if(this.type==='reportees'){
      this.id=this.loginService.username;
      console.log(this.id)
      this.getReportees(this.id);
    }
    
  }

  getCredentials() {
    this.employeeService.loadEmployee()
      .subscribe((credentials) => {
        this.employeeCredentials = credentials;
      });

  }

  getReportees(id){
    this.employeeService.loadReportees(id).subscribe((credentials)=>{
      this.employeeCredentials=credentials;
    })
  }

}
