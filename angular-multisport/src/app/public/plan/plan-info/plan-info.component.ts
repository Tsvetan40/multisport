import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PublicService } from 'src/app/services/public.service';
import { Plan } from 'src/app/models/Plan';

@Component({
  selector: 'app-plan-info',
  templateUrl: './plan-info.component.html',
  styleUrls: ['./plan-info.component.css']
})
export class PlanInfoComponent implements OnInit{
  
  public URLPlanName!: string
  public plan!: Plan

  constructor(private publicService: PublicService, private activatedRoute: ActivatedRoute) { 
    this.activatedRoute.params.subscribe(data => {this.URLPlanName = data['name']})
  }
  
  
  ngOnInit(): void {
      this.publicService.getSinglePlan(this.URLPlanName).subscribe(
        (data) => {
          this.plan = data
        })
    }
    
  subscribePlan() {

    this.publicService.subscribeToPlan(this.plan) 
  }
 }

  

