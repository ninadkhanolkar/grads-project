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
  subscription:Subscription;
  constructor(private adminService:AdminService) { }

  ngOnInit() {
    this.getAdmins();

  }

  getAdmins(){
    this.subscription=this.adminService.getAdmins()
    .subscribe((credentials) => {
      this.filteredAdmins =this.adminCredentials= credentials;
    });
  }

  filter(query: string) {
    this.filteredAdmins=(query) ?
    this.adminCredentials.filter(e=>e.id.toLowerCase().includes(query.toLowerCase())):
    this.adminCredentials;
  }

  removeAdmin(id){
    this.adminService.removeAdmin(id).subscribe((response)=>{
      this.ngOnInit();
    })
  }

}
