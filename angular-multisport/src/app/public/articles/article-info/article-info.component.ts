import { Comment } from '@angular/compiler';
import { Component, EventEmitter, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Article } from 'src/app/models/page/Article';
import { PublicService } from 'src/app/services/public.service';

@Component({
  selector: 'app-article-info',
  templateUrl: './article-info.component.html',
  styleUrls: ['./article-info.component.css']
})
export class ArticleInfoComponent implements OnInit{
  
  private title: string 
  paragraphs: string[]
  article: Article

  constructor(private publicService: PublicService, private route: ActivatedRoute) {
    this.article = new Article()
    this.title = ''
    this.paragraphs = []
  }
  
  ngOnInit(): void {

    this.route.params.subscribe(param => {
      this.title = param['title']
    })

    this.publicService.getSingleArticle(this.title).subscribe(
      data => {
        this.article
          .withContent(data['content'])
          .withTitle(data['title'])
          .withPictureBase64(data['pictureBase64'])
          .withPublishedAt(new Date(data['publishedAt']))
          .withComments(data['comments'])
          this.article.comments.forEach(comment => console.log(comment))
          
        this.initParagraphs(this.article.content)
      }
    )
  }
  
  private initParagraphs(content: string) {
    this.paragraphs = content.split('\n')
  }

  public dateToString() {
    return this.article.publishedAt.toDateString()
  }

  addCommentRequest(event: string) {
  
      const comment = {
        'content': event,
        'articleTitle': this.article.title
      }
      this.publicService.addCommentArticle(comment, this.article.title).subscribe()
  }
}
