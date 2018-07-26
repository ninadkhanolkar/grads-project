import { Component, OnInit } from '@angular/core';
import { AdminService } from '../admin.service';
import { HttpClient } from '@angular/common/http';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-admin-table',
  templateUrl: './admin-table.component.html',
  styleUrls: ['./admin-table.component.css']
})
export class AdminTableComponent implements OnInit {

  adminCredentials: any = [];
  filteredAdmins:any = [];
  constructor(private adminService:AdminService,private subscription:Subscription) { }

  ngOnInit() {

  }

  getAdmins(){
    this.subscription=this.adminService.getAdmins()
    .subscribe((credentials) => {
      this.filteredAdmins =this.adminCredentials= credentials;
    });
  }

}
