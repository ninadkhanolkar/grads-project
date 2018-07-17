import {Component, OnInit, Input} from '@angular/core';
import {EmployeeService} from '../employee.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  @Input() send: any;
  employeeCredentials: any = [];
  constructor(private employeeService: EmployeeService) {}

  ngOnInit() {

  }

 


}
