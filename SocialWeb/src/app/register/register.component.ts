import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray, FormControl } from '@angular/forms';
import { RegistrationService } from '../registration.service';
import { Router, ActivatedRoute } from '@angular/router';
import { AllSkillsService } from '../all-skills.service';
import { Observable } from 'rxjs';
import { FileUploadService } from '../file-upload.service';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  employeeUrl = "/";
  registerForm: FormGroup;
  skills: any = [];
  employeeInfo: any;

  //filteredSkills: Observable<string[]>;

  constructor(private fb: FormBuilder
    , private registrationService: RegistrationService
    , private router: Router
    , private allSkills: AllSkillsService
    , private fileUpload: FileUploadService
    , private route: ActivatedRoute
    , private employeeService: EmployeeService) { }

  ngOnInit() {

    this.route.params.subscribe(params => console.log(params));
    this.employeeUrl = this.route.snapshot.paramMap.get('url');
    console.log(this.employeeUrl);
    if (this.employeeUrl !== "") {
      this.getEmp(this.employeeUrl);
    }

    this.registerForm = this.fb.group({
      empId: ['', Validators.required],
      firstName: ['', Validators.required],
      lastName: [''],
      bioPic: [''],
      bioPicFile: [''],
      gender: ['Male'],
      password: ['', [Validators.required, Validators.minLength(5)]],
      confirmPassword: ['', Validators.required],
      maritalStatus: ['Unmarried'],
      emailId: ['', [Validators.required, Validators.email]],
      dateOfBirth: ['', Validators.required],
      contactNumberPersonal: ['', Validators.min(1000000000)],
      contactNumberWork: ['', [Validators.required, Validators.min(1000000000)]],
      managerId: [''],
      currentPosition: ['', Validators.required],
      address: this.fb.group({
        street: [''],
        city: ['', Validators.required],
        state: [''],
        country: [''],
        zipcode: ['', Validators.required],
      }),
      qualificationDegree: ['', Validators.required],
      instituteName: ['', Validators.required],
      bio: [''],
      skills: this.fb.array([this.createSkill()]),
      certifications: this.fb.array([this.createCertificate()]),
      resume: ['']
    }, { validator: this.checkIfMatchingPasswords('password', 'confirmPassword') });

    this.allSkills.getSkills().subscribe(skills => {
      this.skills = skills;
      console.log(this.skills);
    })

  }

  getEmp(url) {
    this.employeeService.fetchDetails(url)
      .subscribe((employee) => {
        this.employeeInfo = employee;
        console.log(this.employeeInfo);
        this.registerForm.patchValue(this.employeeInfo);
        this.registerForm.controls['address'].patchValue({
          street: this.employeeInfo.addresses[0].street,
          city: this.employeeInfo.addresses[0].city,
          state: this.employeeInfo.addresses[0].state,
          country: this.employeeInfo.addresses[0].country,
          zipcode: this.employeeInfo.addresses[0].zipcode
        });
      });
  }

  fileChange(event) {
    let fileList: FileList = event.target.files;
    this.fileUpload.uploadFile(fileList);

  }


  checkIfMatchingPasswords(passwordKey: string, passwordConfirmationKey: string) {
    return (group: FormGroup) => {
      let passwordInput = group.controls[passwordKey],
        passwordConfirmationInput = group.controls[passwordConfirmationKey];
      if (passwordInput.value !== passwordConfirmationInput.value) {
        return passwordConfirmationInput.setErrors({ notEquivalent: true })
      }
      else {
        return passwordConfirmationInput.setErrors(null);
      }
    }
  }

  createSkill() {
    return this.fb.group({
      allSkillId: ''
    });
  }

  addSkill() {
    (<FormArray>this.registerForm.get('skills')).push(this.createSkill());
  }

  removeSkill(i: number) {
    (<FormArray>this.registerForm.get('skills')).removeAt(i);
  }

  createCertificate() {
    return this.fb.group({
      completionYear: '',
      certificationName: ''
    });
  }

  addCertificate() {
    (<FormArray>this.registerForm.get('certifications')).push(this.createCertificate());
  }

  removeCertificate(i: number) {
    (<FormArray>this.registerForm.get('certifications')).removeAt(i);
  }

  displayFn(skill?): string | undefined {
    return skill ? skill.name : undefined;
  }

  register(e) {
    e.preventDefault();
    // if (this.registerForm.valid) {
    if (true) {
      console.log(JSON.stringify(this.registerForm.value));
      console.log(this.registerForm.value)
      this.registrationService.register(this.registerForm.value).subscribe(e => {
        console.log(e);
      });
      this.router.navigateByUrl("/");
    }


  }
}
