import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'admin-nav',
  templateUrl: './admin-navigation.component.html',
  styleUrls: ['./admin-navigation.component.css']
})
export class AdminNavigationComponent {

  constructor(private router: Router) {}

  displayArticles(): void {
    this.router.navigate(['multisport/admin/articles'])
  }

  displayPlans(): void {
    this.router.navigate(['multisport/admin/plans'])
  }

  displayCenters(): void {
    this.router.navigate(['multisport/admin/centers'])
  }
}
