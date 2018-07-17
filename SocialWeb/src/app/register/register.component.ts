import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray,FormControl } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm:FormGroup;
  //skills:any=[];
  
  constructor(private fb:FormBuilder) { }

  ngOnInit() {
    this.registerForm=this.fb.group({
      empId:['',Validators.required],
      firstName:['',Validators.required],
      lastName:[''],
      email:['',[Validators.required,Validators.email]],
      dob:['',Validators.required],
      mobile:[''],
      workPhone:[''],
      managerId:[''],
      currentPosition:[''],
      street:[''],
      city:[''],
      state:[''],
      country:[''],
      zip:[''],
      education:[''],
      institutionName:[''],
      bio:[''],
      skills:this.fb.array([new FormControl('')]),
      certificates:this.fb.array([new FormControl('')]),
      resume:['']
      

      // skills:this.fb.array([this.createSkill()])
    })
  }
  addSkill() {
    (<FormArray>this.registerForm.get('skills')).push(new FormControl(''));
   }

   removeSkill(i: number) {
    (<FormArray>this.registerForm.get('skills')).removeAt(i);
   }

   addCertificate(){
    (<FormArray>this.registerForm.get('certificates')).push(new FormControl(''));
   }

   removeCertificate(i: number) {
    (<FormArray>this.registerForm.get('certificates')).removeAt(i);
   }

   register(){
     console.log(this.registerForm.value)
   }

 


}
