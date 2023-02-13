import { Component, OnInit } from '@angular/core';
import { Plan } from 'src/app/models/Plan';
import { AdminService } from 'src/app/services/admin.service';
import { Comment } from 'src/app/models/page/Comment';
import { ActivatedRoute } from '@angular/router';
import { CenterType } from 'src/app/models/centers/typecenter/CenterType';
import { AdminComment } from 'src/app/models/page/AdminComment';
import { Status } from 'src/app/models/user/Status';
import { Role } from 'src/app/models/user/Role';

@Component({
  selector: 'app-admin-single-user',
  templateUrl: './admin-single-user.component.html',
  styleUrls: ['./admin-single-user.component.css']
})
export class AdminSingleUserComponent implements OnInit{
  
  id: number
  email: string
  password: string
  password_salt: string
  firstName: string
  secondName: string
  age: number
  status: Status
  role: Role
  plan: Plan | null
  comments: AdminComment[]

  constructor(private adminService: AdminService, private activatedRoute: ActivatedRoute) { 
    this.id = 0
    this.email = ''
    this.password = ''
    this.password_salt = ''
    this.firstName = ''
    this.secondName = ''
    this.age = 0
    this.plan = null
    this.comments = []
    this.status = Status.ACTIVE
    this.role = Role.USER
  }


  ngOnInit(): void {
    
    this.activatedRoute.params.subscribe(
      data => {
        this.id = data['id']
        
        this.adminService.getUserById(this.id).subscribe(
          user => {
            this.id = user.id
            this.email = user.email
            this.password = user.password
            this.password_salt = user.salt
            this.firstName = user.firstName
            this.secondName = user.secondName
            this.age = user.age
            this.role = user.role
            this.status = user.status
            
            user.comments.forEach(comment => {
              this.comments.push(comment)
            })
          }
        )
      }, 
      error => {
        //to do not found page
      }       
    )

 }

  blockUser() {

    this.adminService.blockUser(this.id).subscribe(
      data => {
        data['status'] == Status.BLOCKED ? this.status = Status.BLOCKED : this.status = Status.ACTIVE
      },
      error => {
        alert('cannot change status of user')
      }
    )
  }

  restoreUser() {
    this.adminService.restoreUserRights(this.id).subscribe(
      data => {
        data['status'] == Status.BLOCKED ? this.status = Status.BLOCKED : this.status = Status.ACTIVE
      },
      error => {
        alert('cannot change status of user')
      }
    )
  }
}
