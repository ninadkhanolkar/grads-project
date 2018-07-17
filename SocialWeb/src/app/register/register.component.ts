import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray, FormControl } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;
  //skills:any=[];

  constructor(private fb: FormBuilder) { }

  ngOnInit() {
    this.registerForm = this.fb.group({
      empId: ['', Validators.required],
      firstName: ['', Validators.required],
      lastName: [''],
      bioPic:[''],
      gender:['Male'],
      password:[''],
      confirmPassword:[''],
      maritalStatus:['Unmarried'],
      emailId: ['', [Validators.required, Validators.email]],
      dateOfBirth: ['', Validators.required],
      contactNumberPersonal: [''],
      contactNumberWork: [''],
      managerId: [''],
      currentPosition: [''],
      address: this.fb.group({
        street: [''],
        city: [''],
        state: [''],
        country: [''],
        zipcode: [''],
      }),
      qualificationDegree: [''],
      instituteName: [''],
      bio: [''],
      skills: this.fb.array([this.createSkill()]),
      certifications: this.fb.array([this.createCertificate()]),
      resume: ['']


      // skills:this.fb.array([this.createSkill()])
    })
  }

  createSkill(){
    return this.fb.group({
      allSkillId:''
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

  register(e) {
    e.preventDefault();
    console.log(JSON.stringify(this.registerForm.value))
  }




}
