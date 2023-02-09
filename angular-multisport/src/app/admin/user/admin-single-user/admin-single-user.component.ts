import { Component, OnInit } from '@angular/core';
import { Plan } from 'src/app/models/Plan';
import { AdminService } from 'src/app/services/admin.service';
import { Comment } from 'src/app/models/page/Comment';
import { ActivatedRoute } from '@angular/router';
import { CenterType } from 'src/app/models/centers/typecenter/CenterType';
import { AdminComment } from 'src/app/models/page/AdminComment';

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
  }


  ngOnInit(): void {
    
    this.activatedRoute.params.subscribe(
      data => {
        this.id = data['id']
        
        this.adminService.getUserById(this.id).subscribe(
          user => {
            console.log(user)
            this.id = user.id
            this.email = user.email
            this.password = user.password
            this.password_salt = user.salt
            this.firstName = user.firstName
            this.secondName = user.secondName
            this.age = user.age
            
            user.comments.forEach(comment => {
              const cmnt = comment
              debugger
              this.comments.push(cmnt)
            })
            console.log(user.comments)
          
          }
        )
      }
    )

  }
}
