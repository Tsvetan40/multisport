import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Comment } from 'src/app/models/page/Comment';
import { PublicService } from 'src/app/services/public.service';

@Component({
  selector: 'app-single-relax-center',
  templateUrl: './single-relax-center.component.html',
  styleUrls: ['./single-relax-center.component.css']
})
export class SingleRelaxCenterComponent implements OnInit{
  id: number = 1
  name: string = ''
  address: string = ''
  pictures: string[] = []
  description: string[] = []
  rating: number = 5
  services: string[] = []
  comments: Comment[] = []

  constructor(private route: ActivatedRoute, private publicService: PublicService) {  }

  private splitDescriptionResponse(description: string): string[] {
    return description.split('\n')
  }

  ngOnInit(): void {
    this.route.params.subscribe(
      data => {
        const id: number = data['id']
        this.publicService.getSingleRelaxCenter(id).subscribe(
          center => {
            this.id = center['id']
            this.name = center['name']
            this.address = center['address']
            this.description = this.splitDescriptionResponse(center['description']) 
            this.pictures = center['pictures']
            this.rating = center['rating']
            this.services = center['services']
            this.comments = center['comments']
          }
        )
      }
    )
  }

  addCommentRequest($event: string) {

  }
}
