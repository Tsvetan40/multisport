import { Component, OnInit } from '@angular/core';
import { Article } from 'src/app/models/page/Article';
import { PublicService } from 'src/app/services/public.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-public-articles',
  templateUrl: './public-articles.component.html',
  styleUrls: ['./public-articles.component.css']
})
export class PublicArticlesComponent implements OnInit{
  private baseUrl: string
  allArticles: Article[] 

  constructor(private publicService: PublicService, private router: Router) {
    this.allArticles = []
    this.baseUrl = '/multisport/articles'
   }

   goTo(event: Event) {
      const p = event.target as HTMLParagraphElement;
      const title =  p.textContent

      this.router.navigateByUrl(`${this.baseUrl}/${title}`)  
   }

   ngOnInit(): void {
    this.publicService.getAllArticle().subscribe(
      data => {
        data.forEach(article => {
          this.allArticles.push(article);
        })
      }, 
      error => {
        // to do redirect
      }
      
      )
  

  }


}
