import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PublicService } from 'src/app/services/public.service';
import { Comment } from 'src/app/models/page/Comment';
import { CenterType } from 'src/app/models/centers/typecenter/CenterType';

@Component({
  selector: 'app-single-sport-center',
  templateUrl: './single-sport-center.component.html',
  styleUrls: ['./single-sport-center.component.css']
})
export class SingleSportCenterComponent implements OnInit{
  currImageIndex: number = 0
  id: number = 1
  name: string = ''
  address: string = ''
  pictures: string[] = []
  description: string[] = []
  rating: number = 5
  comments: Comment[] = []

  constructor(private route: ActivatedRoute, private publicService: PublicService) {  }

  private splitDescriptionResponse(description: string): string[] {
    return description.split('\n')
  }

  ngOnInit(): void {
    this.route.params.subscribe(
      data => {
        const id: number = data['id']
        this.publicService.getSingleSportCenter(id).subscribe(
          center => {
            this.id = center['id']
            this.name = center['name']
            this.address = center['address']
            this.description = this.splitDescriptionResponse(center['description']) 
            this.pictures = center['pictures']
            this.rating = center['rating']
            this.comments = center['comments']
          }
        )
      } 
    )
  }

  addCommentRequest(event: string) {
    const comment = {
      'content': event,
      'centerAddress': this.address,
      'typeCenter': 'SportCenter'
    }

    this.publicService.addCommentSportCenter(comment, this.id).subscribe()
  }

  moveLeft() {

    this.currImageIndex--

    if (this.currImageIndex < 0) {
      this.currImageIndex = this.pictures.length -1
    }
  }

  moveRight() {
    this.currImageIndex++

    if (this.currImageIndex == this.pictures.length) {
      this.currImageIndex = 0
    }
  }
}
