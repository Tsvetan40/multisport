import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RelaxCenter } from 'src/app/models/centers/RelaxCenter';
import { PublicService } from 'src/app/services/public.service';

@Component({
  selector: 'public-relax-centers',
  templateUrl: './public-relax-centers.component.html',
  styleUrls: ['./public-relax-centers.component.css']
})
export class PublicRelaxCentersComponent implements OnInit{

  private readonly url: string = 'multisport/relax-centers'
  relaxCenters: RelaxCenter[] 

  constructor(private publicService: PublicService, private route: Router) {
    this.relaxCenters = []
  }

  ngOnInit(): void {
    this.publicService.getAllRelaxCenters().subscribe(
      data => {
        data.forEach(center => {
          this.relaxCenters.push(center)
        })
      }
    )
  }

  goToRelaxCenter(i: number) {
    const id = this.relaxCenters[i].id

    this.route.navigateByUrl(`${this.url}/${id}`)
  }
}
