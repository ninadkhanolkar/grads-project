import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';
import { RegistrationService } from '../registration.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  credentialForm: FormGroup;
  nullurl: String = "";
  invalid:boolean=false;
  registered:boolean=false;
  private credential = { username: '', password: '' };

  constructor(private fb: FormBuilder, private router: Router, private loginService: LoginService,private registrationService:RegistrationService) { }

  ngOnInit() {

    if(sessionStorage.getItem('username')){
      if (sessionStorage.getItem("requestedRole") === 'Admin' && sessionStorage.getItem("roles").indexOf('ROLE_ADMIN')>=0) {
        this.router.navigate(['admin/profile']);
      }
      else if (sessionStorage.getItem("requestedRole") === 'Employee' &&sessionStorage.getItem("roles").indexOf('ROLE_USER')>=0) {
        this.router.navigate(['employee/profile/profile-info']);
      }
    }
    this.credentialForm = this.fb.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required]],
      role: ['Employee']
    });
  }
 
  isInvalid(){
    return this.invalid;
  }

  isRegistered(){
    return this.registered;
  }

  ngDoCheck(){
    console.log("in do check")
    this.invalid=sessionStorage.getItem('invalidCredentials')==="true"
    this.registered=sessionStorage.getItem('registered')==="true"
  }


  onSubmit() {
    let username = this.credentialForm.value['username'];
    let password = this.credentialForm.value['password'];
    this.credential = { username, password };
    let userRole = this.credentialForm.get('role').value
    this.loginService.tryLogin(this.credential,userRole);

    // this.userrole = 'Admin';
  }
}