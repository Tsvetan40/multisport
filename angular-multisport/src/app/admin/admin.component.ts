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
  showCenters = false


  displayPlans(): void {
    this.showPlan = !this.showPlan
    this.showCenters = this.showArticles = this.showUsers = false
  }

  displayArticles(): void {
    this.showArticles = !this.showArticles
    this.showCenters = this.showPlan = this.showUsers = false
  }
  
  displayUsers(): void {
    this.showUsers = !this.showUsers
    this.showCenters = this.showArticles = this.showPlan = false
  }

  displayCenters(): void {
    this.showCenters = !this.showCenters;
    this.showArticles = this.showPlan = this.showUsers = false
  }
}
