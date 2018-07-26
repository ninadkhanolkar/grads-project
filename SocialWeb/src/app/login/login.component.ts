import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  credentialForm: FormGroup;
  nullurl: String = "";
  private credential = { username: '', password: '' };

  constructor(private fb: FormBuilder, private router: Router, private loginService: LoginService) { }

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

  ngAfter

  onSubmit() {
    let username = this.credentialForm.value['username'];
    let password = this.credentialForm.value['password'];
    this.credential = { username, password };
    let userRole = this.credentialForm.get('role').value
    this.loginService.tryLogin(this.credential,userRole);

    // this.userrole = 'Admin';
  }
}