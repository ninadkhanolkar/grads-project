import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray, FormControl } from '@angular/forms';
import { RegistrationService } from '../registration.service';
import { Router } from '@angular/router';
import { AllSkillsService } from '../all-skills.service';
import { Observable } from 'rxjs';
import { FileUploadRetreiveService } from '../file-upload-retreive.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;
  skills: any = [];
  resume: File;
  bioPic:File;
  //filteredSkills: Observable<string[]>;

  constructor(private fb: FormBuilder
    , private registrationService: RegistrationService
    , private router: Router
    , private allSkills: AllSkillsService
    , private fileUpload: FileUploadRetreiveService) { }

  ngOnInit() {
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

  resumeFileChange(event) {
    this.resume = event.target.files.item(0);
  }

  bioPicFileChange(event) {
    this.bioPic = event.target.files.item(0);
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
      if (this.resume) {
        this.fileUpload.uploadFile(this.resume, this.registerForm.get('empId').value,"resume").subscribe(e => {
          console.log(e);
        });
      }
      this.fileUpload.uploadFile(this.bioPic, this.registerForm.get('empId').value,"bioPic").subscribe(e => {
        console.log(e);
      });

      //this.registerForm.get('resume').setValue('userFiles/'+this.registerForm.get('empId')+'resume')
      console.log(JSON.stringify(this.registerForm.value));
      console.log(this.registerForm.value)
      this.registrationService.register(this.registerForm.value).subscribe(e => {
        //console.log(e);
      });



      this.router.navigateByUrl("/");
    }


  }
}
