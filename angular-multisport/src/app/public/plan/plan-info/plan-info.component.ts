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
    //when error occurs return to error page
    
        this.publicService.getSinglePlan(this.URLPlanName).subscribe(
          (data) => {
            this.plan = data
            console.log(this.plan)
          })
      }
    

 }

  

