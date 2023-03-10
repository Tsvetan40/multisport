import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
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

  constructor(private publicService: PublicService, private route: ActivatedRoute, private router: Router) {
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
          if (data.comments != null) {
            this.article.withComments(data['comments'].sort((a, b ) => {
              return new Date(a.publishedAt).getTime() - new Date(b.publishedAt).getTime()
          }))
          }
          
          this.initParagraphs(this.article.content)
      }, 
      error => {
        if (error['status'] == 403) {
          alert("You must be logged in to post a comment")
        } else if (error['status'] == 404) {
          this.router.navigateByUrl(`multisport/not-found`)
        } else {
          alert("Not able to add comment")
        }
      })
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
      this.publicService.addCommentArticle(comment, this.article.title).subscribe(
        data => {
          this.article.comments.push(data)
        }, 
        error => {
          if (error['status'] == 403) {
            alert("You must be logged in to add a comemnt")
          } else {
            alert('can not add a comment')
          }
        }
      )
  }
}
