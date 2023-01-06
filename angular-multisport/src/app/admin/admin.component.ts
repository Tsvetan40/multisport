import { Component, OnInit } from '@angular/core';
import { User } from '../models/User';
import { AdminServiceService } from '../services/admin-service.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit{
  
  user!: User
  
  constructor(private adminService: AdminServiceService) {}
  
  ngOnInit(): void {
    console.log("OnInit") 
    this.user = this.adminService.getUser();
  }

}
