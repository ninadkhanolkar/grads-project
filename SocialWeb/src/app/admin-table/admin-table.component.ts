import { Component, OnInit } from '@angular/core';
import { AdminService } from '../admin.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-admin-table',
  templateUrl: './admin-table.component.html',
  styleUrls: ['./admin-table.component.css']
})
export class AdminTableComponent implements OnInit {

  constructor(private adminService:AdminService) { }

  ngOnInit() {

  }

  getAdmins(){
     this.adminService.getAdmins()
  }

}
