import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SportCenter } from 'src/app/models/centers/SportCenter';
import { PublicService } from 'src/app/services/public.service';


@Component({
  selector: 'app-single-sport-center',
  templateUrl: './single-sport-center.component.html',
  styleUrls: ['./single-sport-center.component.css']
})
export class SingleSportCenterComponent implements OnInit{

  id: number = 1
  name: string = ''
  address: string = ''
  pictures: string[] = []
  description: string[] = []
  rating: number = 5

  constructor(private route: ActivatedRoute, private publicService: PublicService) { 
    
  }

  splitDescriptionResponse(description: string): string[] {
    return description.split('\n')
  }

  ngOnInit(): void {
    this.route.params.subscribe(
      data => {
        const id: number = data['id']
        this.publicService.getSingleSportCenter(id).subscribe(
          data => {
            this.id = data['id']
            this.name = data['name']
            this.address = data['address']
            this.description = this.splitDescriptionResponse(data['description']) 
            this.pictures = data['pictures']
            this.rating = data['rating']
          }
        )
      } 
    )
  }
}
