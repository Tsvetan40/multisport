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
    this.plan = new Plan('', 0, [])
  }
  
  
  ngOnInit(): void {
      this.publicService.getSinglePlan(this.URLPlanName).subscribe(
        (data) => {
          this.plan = data
        },
        error => {
          // to do return not found page
        }
        )
    }
    
  subscribePlan() {

    this.publicService.subscribeToPlan(this.plan).subscribe(
      data => {
        alert('Congratulations! You have subscribed to this plam!')
      },
      error => {
        if (error['status'] == 403) {
          alert('You must be logged to subscribe to plan!')
        } else {
          //to do redirect
        }
      }
    )
  }
 }

  

