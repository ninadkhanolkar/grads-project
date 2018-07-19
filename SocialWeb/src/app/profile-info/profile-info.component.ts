import {Component, OnInit} from '@angular/core';
import {EmployeeService} from '../employee.service';
import {Router, ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-profile-info',
  templateUrl: './profile-info.component.html',
  styleUrls: ['./profile-info.component.css']
})
export class ProfileInfoComponent implements OnInit {

  constructor(private employeeService: EmployeeService, private route: ActivatedRoute) {
    const url = this.route.snapshot.paramMap.get('p1');
    this.getEmp(url);
  }

  employeeInfo: any;
  id: any;
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
    const url = this.route.snapshot.paramMap.get('p1');
    console.log(url);

  }

  getEmp(url) {
    this.employeeService.fetchDetails(url)
      .subscribe((employee) => {
        this.employeeInfo = employee;
        console.log(employee);
        this.id = this.employeeInfo.empId;
        this.bio = this.employeeInfo.bio;
        this.city = employee.addresses[0].city;
        this.firstName = this.employeeInfo.firstName;
        this.lastName = this.employeeInfo.lastName;
        this.degree = this.employeeInfo.qualificationDegree;
        this.institute = this.employeeInfo.instituteName;
        this.employeeName = this.firstName + " " + this.lastName;
        this.currentPosition = employee.currentPosition;
        this.emailId = this.employeeInfo.emailId;
        this.contact = this.employeeInfo.contactNumberPersonal;
        this.phone = this.employeeInfo.contactNumberWork;
        employee.certifications.forEach((c)=>{
          console.log(c)
        this.certifications.push(c);
        });
         employee.skills.forEach((s)=>{
        this.skillsStack.push(s.allSkill);
           console.log(s.allSkill);
        });
        console.log(this.certifications);
      });
  }
}


//{
//  "addresses": [
//    {
//      "addressId": 0,
//      "city": "string",
//      "country": "string",
//      "state": "string",
//      "street": "string",
//      "zipcode": 0
//    }
//  ],
//  "applicationStatus": 0,
//  "bio": "string",
//  "bioPic": "string",
//  "certifications": [
//    {
//      "certificationId": 0,
//      "certificationName": "string",
//      "completionYear": 0
//    }
//  ],
//  "contactNumberPersonal": 0,
//  "contactNumberWork": 0,
//  "currentPosition": "string",
//  "dateOfBirth": "2018-07-19T04:32:18.494Z",
//  "emailId": "string",
//  "empId": "string",
//  "employees": [
//    {}
//  ],
//  "firstName": "string",
//  "gender": "string",
//  "instituteName": "string",
//  "lastName": "string",
//  "maritalStatus": "string",
//  "qualificationDegree": "string",
//  "resume": "string",
//  "skills": [
//    {
//      "allSkill": {
//        "allSkillId": 0,
//        "name": "string"
//      },
//      "skillId": 0
//    }
//  ]
//}
