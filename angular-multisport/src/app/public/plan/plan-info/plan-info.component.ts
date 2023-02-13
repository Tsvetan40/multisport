import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PublicService } from 'src/app/services/public.service';
import { Plan } from 'src/app/models/Plan';

@Component({
  selector: 'app-plan-info',
  templateUrl: './plan-info.component.html',
  styleUrls: ['./plan-info.component.css']
})
export class PlanInfoComponent implements OnInit{
  private readonly baseUrl: string
  public URLPlanName!: string
  public plan!: Plan

  constructor(private publicService: PublicService, private activatedRoute: ActivatedRoute, private router: Router) {
    this.activatedRoute.params.subscribe(data => {this.URLPlanName = data['name']})
    this.plan = new Plan('', 0, [])
    this.baseUrl = '/multisport/plans'
  }


  ngOnInit(): void {
      this.publicService.getSinglePlan(this.URLPlanName).subscribe(
  
        (data) => {
          debugger
          this.plan = data
        },
        error => {
          debugger
          this.router.navigate([`multisport/not-found`])
        }
      )
    }

  subscribePlan() {

    this.publicService.subscribeToPlan(this.plan).subscribe(
      data => {
        alert('Congratulations! You have subscribed to this plan!')
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



