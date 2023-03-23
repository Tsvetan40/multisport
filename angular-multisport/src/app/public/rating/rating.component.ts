import { Component, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { faStar as regularStar} from '@fortawesome/free-regular-svg-icons';
import { faStar as solidStar } from '@fortawesome/free-solid-svg-icons';
import { PublicService } from 'src/app/services/public.service';
import { Star } from './star';

@Component({
  selector: 'app-rating',
  templateUrl: './rating.component.html',
  styleUrls: ['./rating.component.css']
})
export class RatingComponent {
  private isClicked = false
  private readonly starRatingLength = 5
  currClickNumber: number = 0
  applyCssStyle: boolean = false
  starContainer: Array<Star> = []
  @Input() rating: number = 0;
  private centerId!: number
  
  constructor(private publicService: PublicService, private route: ActivatedRoute) {
    for(let i = 0; i < this.starRatingLength; ++i) {
      this.starContainer.push(new Star())
    }

    this.route.params.subscribe(
      data => {
        this.centerId = data['id']
      } 
    )
  }

  click(i: number) {
   console.log('clicked')
    this.currClickNumber = i    
    this.isClicked = true

    const applyCss = () => {
      this.applyCssStyle = !this.applyCssStyle
    }

    async function cssStyleApply() {
      const myTimeOut = setTimeout(() => {
        applyCss()
      }, 10)

      const myTimeOutex = setTimeout(() => {
        applyCss()
      }, 2000)

    }

    cssStyleApply()
    
    console.log(this.currClickNumber, this.centerId)

    this.publicService.rate(this.currClickNumber, this.centerId).subscribe(
      data => {
        this.rating = data
      }, error => {
        if (error['status'] == 403) {
          alert("You must be logged in to rate")
        } else {
          alert("Rating the center cannot be done")
        }
      }
    )
  }

  shouldChange(i: number): boolean {
    return i <= this.currClickNumber && this.isClicked
  }
}
