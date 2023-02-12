import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SportCenter } from 'src/app/models/centers/SportCenter';
import { PublicService } from 'src/app/services/public.service';

@Component({
  selector: 'public-sport-centers',
  templateUrl: './public-sport-centers.component.html',
  styleUrls: ['./public-sport-centers.component.css']
})
export class PublicSportCentersComponent implements OnInit{

  private readonly url: string = 'multisport/sport-centers'
  sportCenters: SportCenter[]
  
  constructor(private publicService: PublicService, private router: Router) {
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

  goToSportCenter(i: number): void {
      const id =  this.sportCenters[i].id

      this.router.navigateByUrl(`${this.url}/${id}`)
  }
}
