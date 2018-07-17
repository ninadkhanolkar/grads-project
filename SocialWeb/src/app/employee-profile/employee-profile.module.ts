import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './home/home.component';
import { ReporteesListComponent } from './reportees-list/reportees-list.component';
import { ReporteeComponent } from './reportee/reportee.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [HomeComponent, ReporteesListComponent, ReporteeComponent]
})
export class EmployeeProfileModule { }
