import { Component } from '@angular/core';
import { faStar as regularStar} from '@fortawesome/free-regular-svg-icons';
import { faStar as solidStar } from '@fortawesome/free-solid-svg-icons';
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
  
  constructor() {
    for(let i = 0; i < this.starRatingLength; ++i) {
      this.starContainer.push(new Star())
    }
  }

  


  click(i: number) {
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

  }

  shouldChange(i: number): boolean {
    return i <= this.currClickNumber && this.isClicked
  }
}
