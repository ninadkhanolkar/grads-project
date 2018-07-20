import {Component, OnInit, Input} from '@angular/core';
import {EmployeeService} from '../employee.service';
import {Router, ActivatedRoute} from '@angular/router';

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
              private route:ActivatedRoute) {}

  ngOnInit() {
    this.type=this.route.snapshot.paramMap.get('type');
    console.log(this.type)
    if(this.type ==='employees'){
      this.getCredentials();
    }
    else if(this.type==='reportees'){
      this.id=this.route.snapshot.paramMap.get('id');
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
