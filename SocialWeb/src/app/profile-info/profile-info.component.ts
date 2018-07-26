import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../employee.service';
import { Router, ActivatedRoute } from '@angular/router';
import { FileUploadRetreiveService } from '../file-upload-retreive.service';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-profile-info',
  templateUrl: './profile-info.component.html',
  styleUrls: ['./profile-info.component.css']
})
export class ProfileInfoComponent implements OnInit {

  constructor(private employeeService: EmployeeService, private route: ActivatedRoute, private fileService: FileUploadRetreiveService,
    private loginService: LoginService) { }
  employeeInfo: any;
  empId: any;
  id: any;
  name: any;
  resume:any;
  image: any;
  url1 = 'http://localhost:8080/api/wiseconnect/v1/file/';
  
  bio: any;
  addresses: any;
  city: any;
  employeeName: any;
  degree: any;
  institute: any;
  currentPosition: any;
  certifications: any = [];
  emailId: any;
  firstName: any;
  lastName: any;
  contact: any;
  phone: any;
  country: any;
  skillsStack: any = [];
  isEditingProfile = false;
  role: String;

  ngOnInit() {
    this.empId = sessionStorage.getItem("username");
    console.log(this.empId);
    this.getEmp(this.empId);
    console.log("In profile-info" + window["sessionStorage"].getItem("username"))
    this.role = sessionStorage.getItem("requestedRole");
  }
  
  
  getEmp(empId) {
    this.employeeService.fetchDetails(empId)
      .subscribe((employee) => {
        this.employeeInfo = employee;
        console.log(employee);
        this.id = this.employeeInfo.empId;

        this.fileService.getFile(this.id, 'bioPic').subscribe((e) => {
          this.createImageFromBlob(e);
        }, error => {
          console.log(error);
        })
         this.url1=this.url1+this.id+'/resume';
        this.fileService.getFile(this.id, 'resume').subscribe((e) => {
          this.createPdfFromBlob(e);
        }, error => {
          console.log(error);
        })
       
       
        this.bio = this.employeeInfo.bio;
        if (this.employeeInfo.addresses[0])
          this.city = this.employeeInfo.addresses[0].city;
        this.country = this.employeeInfo.addresses[0].country;
        this.firstName = this.employeeInfo.firstName;
        this.lastName = this.employeeInfo.lastName;
        this.degree = this.employeeInfo.qualificationDegree;
        this.institute = this.employeeInfo.instituteName;
        this.employeeName = this.firstName + " " + this.lastName;
        this.currentPosition = this.employeeInfo.currentPosition;
        this.emailId = this.employeeInfo.emailId;
        this.contact = this.employeeInfo.contactNumberPersonal;
        this.phone = this.employeeInfo.contactNumberWork;
        this.employeeInfo.certifications.forEach((c) => {
          console.log(c)
          this.certifications.push(c);
        });
        this.employeeInfo.skills.forEach((s) => {
          this.skillsStack.push(s.allSkill);
          console.log(s.allSkill);
        });
        console.log(this.certifications);
      });
  }
  createImageFromBlob(image: Blob) {
    let reader = new FileReader();
    reader.addEventListener("load", () => {
      this.image = reader.result;
    }, false);

    if (image) {
      reader.readAsDataURL(image);
    }
  }
  
  createPdfFromBlob(resume: Blob) {
    let reader = new FileReader();
    reader.addEventListener("load", () => {
      this.resume = reader.result;
    }, false);

    if (resume) {
      reader.readAsDataURL(resume);
    }
  }
  

  toggleIsEdittingProfile() {
    this.isEditingProfile = !this.isEditingProfile;
  }

  isEmployee() {
    if (this.role === "Employee") {
      return true;
    }
    else
      return false;
  }
}
