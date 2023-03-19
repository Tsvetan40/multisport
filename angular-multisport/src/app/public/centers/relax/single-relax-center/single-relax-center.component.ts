import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CenterType } from 'src/app/models/centers/typecenter/CenterType';
import { Comment } from 'src/app/models/page/Comment';
import { PublicService } from 'src/app/services/public.service';

@Component({
  selector: 'app-single-relax-center',
  templateUrl: './single-relax-center.component.html',
  styleUrls: ['./single-relax-center.component.css']
})
export class SingleRelaxCenterComponent implements OnInit{
  currentImageIndex: number = 0
  id: number = 1
  name: string = ''
  address: string = ''
  pictures: string[] = []
  description: string[] = []
  rating: number = 5
  services: string[] = []
  comments: Comment[] = []

  constructor(private route: ActivatedRoute, private publicService: PublicService, private router: Router) {  }

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
            if (center.comments != null) {
              this.comments = center['comments'].sort((a, b) => {
                return new Date(a.publishedAt).getTime() - new Date(b.publishedAt).getTime()
              })
            }

          },
          error => {
            this.router.navigateByUrl(`multisport/not-found`)
          }
        )
      }
    )
  }

  addCommentRequest(event: string) {
      const comment = {
        'content': event,
        'centerAddress': this.address,
        'typeCenter': 'RelaxCenter'
      }

      this.publicService.addCommentRelaxCenter(comment, this.id).subscribe(
        data => {
          this.comments.push(data);
        }, 
        error => {
          if (error['status'] == 403) {
            alert("You are either blocked or not logged")
          } else {
            alert("Not able to add comment")
          }
        }
  
      )
  }

  moveLeft() {
    this.currentImageIndex--
    if (this.currentImageIndex < 0) {
      this.currentImageIndex = this.pictures.length - 1
    }
  }

  moveRight() {
    this.currentImageIndex++
    if (this.currentImageIndex == this.pictures.length) {
      this.currentImageIndex = 0
    }
  }
}
