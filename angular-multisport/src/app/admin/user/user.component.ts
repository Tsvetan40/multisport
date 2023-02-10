import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { PatternService } from 'src/app/services/pattern.service';
import { RegisteredUser } from 'src/app/models/user/RegisteredUser';
import { Status } from 'src/app/models/user/Status';
import { Role } from 'src/app/models/user/Role';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent {
  
  hasAuthenticationError: boolean = false
  firstName: string = ''
  secondName: string = ''
  password: string = ''
  age: number = 0
  id!: number
  email!: string
  private readonly url = '/multisport/admin'
  @ViewChild('addAdminForm') form!: NgForm

  constructor(private route: Router, private patternService: PatternService, private adminService: AdminService) { }


  searchById() {
    this.route.navigateByUrl(`${this.url}/users/${this.id}`)
  }

  searchByEmail() {

  }

  hasErrorName():boolean {
    return this.patternService.hasErrorName(this.firstName)
  }

  hasErrorSecondName(): boolean {
    return this.patternService.hasErrorName(this.secondName)
  }

  hasErrorPassword(): boolean {
    return this.patternService.hasErrorPassword(this.password)
  }

  addAdmin() {
    const admin = new RegisteredUser(this.email, this.password, this.firstName, this.secondName, this.age, Status.ACTIVE, Role.ADMIN)
    this.adminService.addAdmin(admin).subscribe(
      data => {
        this.form.control.reset()
        alert('admin added!')
      }, 
      error => {
        if (error['status'] == 403) {
          this.route.navigate(['/multisport'])
        } else {
          alert("Admin Not Added")
        }
      }
    )
  }
}
