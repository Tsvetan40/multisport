import { Component, OnInit } from '@angular/core';
import { PublicService } from 'src/app/services/public.service';
import { Plan } from 'src/app/models/Plan';
import { Router } from '@angular/router';

@Component({
  selector: 'app-public-plans',
  templateUrl: './public-plans.component.html',
  styleUrls: ['./public-plans.component.css']
})
export class PublicPlansComponent implements OnInit{
  private readonly url = 'multisport/plans/'

  plans: Plan[]
  myImage: any
  title = 'toolset'

  constructor(private publicService: PublicService, private route: Router) { 
    this.plans = []
  }

  ngOnInit(): void {
    
    this.publicService.getAllPlans().subscribe(
      (data) => {
        data.forEach(plan => {
          this.plans.push(plan)
        })
      }, 
      error => {
        this.route.navigateByUrl(`multisport/not-found`)
      }
    )
    
  }
  goToPlan(i: number) {
    const planName = this.plans[i].name
    this.route.navigateByUrl(`${this.url}${planName}`)
  }
  
}
