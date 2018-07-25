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

    this.credentialForm = this.fb.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required]],
      role: ['Employee']
    });
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