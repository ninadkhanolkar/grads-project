import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../employee.service';
import { Router, ActivatedRoute } from '@angular/router';
import { FileUploadRetreiveService } from '../file-upload-retreive.service';

@Component({
  selector: 'app-profile-info',
  templateUrl: './profile-info.component.html',
  styleUrls: ['./profile-info.component.css']
})
export class ProfileInfoComponent implements OnInit {

  constructor(private employeeService: EmployeeService, private route: ActivatedRoute, private fileService: FileUploadRetreiveService) { }
  employeeInfo: any;
  id: any;
  name: any;
  image: any;
  url = 'http://localhost:8080/api/wiseconnect/v1/file/';

  ngOnInit() {
    const url = this.route.snapshot.paramMap.get('p1');
    console.log(url);
    this.getEmp(url);
  }

  getEmp(url) {
    this.employeeService.fetchDetails(url)
      .subscribe((employee) => {
        this.employeeInfo = employee;
        console.log(employee);
        this.id = this.employeeInfo.empId;
        this.fileService.getFile(this.id, 'bioPic').subscribe((e) => {
          this.createImageFromBlob(e); 
        }, error => {  
          console.log(error);
        })
        this.url = this.url + this.id + "/" + "bioPic";
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

}
