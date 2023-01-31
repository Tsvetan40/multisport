import { Component, OnInit } from '@angular/core';
import { PublicService } from 'src/app/services/public.service';
import { Plan } from 'src/app/models/Plan';

@Component({
  selector: 'app-public-plans',
  templateUrl: './public-plans.component.html',
  styleUrls: ['./public-plans.component.css']
})
export class PublicPlansComponent implements OnInit{
  
  private plans: Plan[]
  myImage: any
  title = 'toolset'

  constructor(private publicService: PublicService) { 
    this.plans = []
  }

  ngOnInit(): void {
    
    this.publicService.getAllPlans().subscribe(
      (data) => {
        data.forEach(plan => {
          this.plans.push(plan)
        })

        const pplan = this.plans[0]
        console.log(pplan['imageBase64'])
        this.myImage = pplan['imageBase64']

      }
    )
    
  }

  
}
