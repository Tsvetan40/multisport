import { Component, OnInit } from '@angular/core';
import { SportCenter } from 'src/app/models/centers/SportCenter';
import { PublicService } from 'src/app/services/public.service';

@Component({
  selector: 'public-sport-centers',
  templateUrl: './public-sport-centers.component.html',
  styleUrls: ['./public-sport-centers.component.css']
})
export class PublicSportCentersComponent implements OnInit{

  sportCenters: SportCenter[]
  
  constructor(private publicService: PublicService) {
    this.sportCenters = []
   }

  ngOnInit(): void {
    this.publicService.getAllSportCenters().subscribe(
      data =>  {
          data.forEach(sportCenter => {
            this.sportCenters.push(sportCenter)
          })
      }
    )
  }
}
