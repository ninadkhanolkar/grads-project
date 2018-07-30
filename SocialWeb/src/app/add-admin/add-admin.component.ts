import { Component, OnInit } from '@angular/core';
import { Validators, FormGroup, FormBuilder } from '@angular/forms';
import { AdminService } from '../admin.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-add-admin',
  templateUrl: './add-admin.component.html',
  styleUrls: ['./add-admin.component.css']
})
export class AddAdminComponent implements OnInit {

  adminForm: FormGroup
  constructor(private fb: FormBuilder,private adminService:AdminService,private router:Router
             ,private route:ActivatedRoute) { }

  ngOnInit() {
    this.adminForm = this.fb.group({
      id: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(5)]],
      confirmPassword: ['', Validators.required],
    }, { validator: this.checkIfMatchingPasswords('password', 'confirmPassword') });

  }
  checkIfMatchingPasswords(passwordKey: string, passwordConfirmationKey: string) {
    return (group: FormGroup) => {
      let passwordInput = group.controls[passwordKey],
        passwordConfirmationInput = group.controls[passwordConfirmationKey];
      if (passwordInput.value !== passwordConfirmationInput.value) {
        // this.passwordError=true;
        // this.passwordErrorMessage="Password and Confirm Password don't match";
        return passwordConfirmationInput.setErrors({ notEquivalent: true })
      }
      else {
        return passwordConfirmationInput.setErrors(null);
      }
    }
  }
  add(e) {
    e.preventDefault();
    if (this.adminForm.valid) {
      // if (true) {
      let payload=this.adminForm.value
      delete payload['confirmPassword'];
      payload['role']={
        roleId: 1
      }
      this.adminService.addAdmin(payload).subscribe(e => {
        this.router.navigate(['../'],{ relativeTo: this.route })
      });
      // sessionStorage.setItem('registered', "true")
      // this.router.navigateByUrl("/");
    }
    if (this.adminForm.invalid) {
      return;
    }
  }
}

