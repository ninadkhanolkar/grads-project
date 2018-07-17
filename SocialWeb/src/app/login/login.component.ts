import {Component, OnInit, Output, EventEmitter} from '@angular/core';
import {FormGroup, FormBuilder, Validators} from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  credentialForm: FormGroup;
  
  private credential = {'username': '', 'password': ''};

  constructor(private fb: FormBuilder) {}

  ngOnInit() {

    this.credentialForm = this.fb.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required]]

    });

  }

  
}
