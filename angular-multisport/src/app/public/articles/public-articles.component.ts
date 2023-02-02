import { Component, OnInit } from '@angular/core';
import { Article } from 'src/app/models/page/Article';
import { PublicService } from 'src/app/services/public.service';

@Component({
  selector: 'app-public-articles',
  templateUrl: './public-articles.component.html',
  styleUrls: ['./public-articles.component.css']
})
export class PublicArticlesComponent implements OnInit{

  allArticles: Article[] 
  article: Article

  constructor(private publicService: PublicService) {
    this.allArticles = []
    this.article = new Article()
   }
  
   ngOnInit(): void {
    this.publicService.getAllArticle().subscribe(
      (data) => {
        data.forEach(article => {
          this.allArticles.push(article);
        })

      })
  

  }


}
