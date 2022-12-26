import { Component } from '@angular/core';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent {
  showPlan: boolean = false
  showArticles: boolean = false
  showUsers: boolean = false



  displayPlans() {
    this.showPlan = !this.showPlan
    this.showArticles = this.showUsers = false
  }

  displayArticles() {
    this.showArticles = !this.showArticles
    this.showPlan = this.showUsers = false
  }
  
  displayUsers() {
    this.showUsers = !this.showUsers
    this.showArticles = this.showPlan = false
  }
}
