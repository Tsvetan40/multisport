import { Component, OnInit } from '@angular/core';
import { User } from '../models/User';
import { AdminService } from '../services/admin.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit{
  
  user!: User

  constructor(private adminService: AdminService) {}
  
  ngOnInit(): void {
    this.user = this.adminService.getUser()
    console.log(this.user)
  }

}
