import {Component, OnInit, Output, EventEmitter} from '@angular/core';
import {FormGroup, FormBuilder, Validators} from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  credentialForm: FormGroup;
  nullurl : String = "";
  userrole: String;
  private credential = {'username': '', 'password': ''};

  constructor(private fb: FormBuilder) {}

  ngOnInit() {

    this.credentialForm = this.fb.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required]],
      role : ['Employee']
    });
    this.onSubmit();
  }

  onSubmit(){
    this.userrole = this.credentialForm.value['role'];
    this.userrole = 'Admin';
    console.log(this.userrole);
  }
}