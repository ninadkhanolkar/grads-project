import {Component, OnInit} from '@angular/core';
import {EmployeeService} from '../employee.service';
import {Router, ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-profile-info',
  templateUrl: './profile-info.component.html',
  styleUrls: ['./profile-info.component.css']
})
export class ProfileInfoComponent implements OnInit {


  constructor(private employeeService: EmployeeService, private route: ActivatedRoute) {}
  employeeInfo: any;
  id:any;
  url:any;
  bio: any;
  addresses: any;
  name: any;
  city: any;
  employeeName: any;
  degree: any;
  institute: any;
  currentPosition: any;
  certifications: any=[];
  emailId: any;
  firstName:any;
  lastName:any; 
  contact:any;
  phone:any;
  skillsStack:any=[];
  
  ngOnInit() {
    this.url = this.route.snapshot.paramMap.get('p1');
    console.log(this.url);
    this.getEmp(this.url);
  }

  getEmp(url) {
    this.employeeService.fetchDetails(url)
      .subscribe((employee) => {
        this.employeeInfo = employee;
        console.log(employee);
        this.id = this.employeeInfo.empId;
        this.bio = this.employeeInfo.bio;
        this.city = this.employeeInfo.addresses[0].city;
        this.firstName = this.employeeInfo.firstName;
        this.lastName = this.employeeInfo.lastName;
        this.degree = this.employeeInfo.qualificationDegree;
        this.institute = this.employeeInfo.instituteName;
        this.employeeName = this.firstName + " " + this.lastName;
       this.currentPosition = this.employeeInfo.currentPosition;
        this.emailId = this.employeeInfo.emailId;
        this.contact = this.employeeInfo.contactNumberPersonal;
        this.phone = this.employeeInfo.contactNumberWork;
        this.employeeInfo.certifications.forEach((c)=>{
          console.log(c)
        this.certifications.push(c);
        });
         this.employeeInfo.skills.forEach((s)=>{
        this.skillsStack.push(s.allSkill);
           console.log(s.allSkill);
        });
        console.log(this.certifications);
      });
  }
}
