import {Component, OnInit, Output, EventEmitter} from '@angular/core';
import {FormGroup, FormBuilder, Validators} from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  credentialForm: FormGroup;
  nullurl : String = "";
  private credential = {username :'', password: ''};

  constructor(private fb: FormBuilder,private router:Router) {}

  ngOnInit() {

    this.credentialForm = this.fb.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required]],
      role : ['Employee']
    });
  }

  onSubmit(){
    let username=this.credentialForm.value['username'];
    let password=this.credentialForm.value['password'];
    this.credential={username,password};
    let userRole = this.credentialForm.get('role').value
    if(userRole==='Admin'){
      this.router.navigate(['admin/profile',{'role':userRole,'username':username}]);
    }
    if(userRole==='Employee'){
      this.router.navigate(['employee/profile',{'role':userRole,'username':username}]);
    }
   
    // this.userrole = 'Admin';
    console.log();
  }
}