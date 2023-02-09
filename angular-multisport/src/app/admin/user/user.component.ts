import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent {
  
  private readonly url = '/multisport/admin'
  id!: number
  email!: string

  constructor(private route: Router) { }


  searchById() {
    this.route.navigateByUrl(`${this.url}/users/${this.id}`)
  }

  searchByEmail() {

  }
}
