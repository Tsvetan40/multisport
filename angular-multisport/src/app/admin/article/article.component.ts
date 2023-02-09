import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Article } from 'src/app/models/page/Article';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-article',
  templateUrl: './article.component.html',
  styleUrls: ['./article.component.css']
})
export class ArticleComponent {
  title!: string
  text!: string
  errorMessage!: string 
  showAddArticle: boolean
  showAllArticles: boolean
  picture!: File
  @ViewChild('articleForm') formArticle!: NgForm

  constructor(private adminService: AdminService, private router: Router) {
    this.showAddArticle = true
    this.showAllArticles = false
  }

  displayAllArticles(): void {
    this.showAllArticles = true
    this.showAddArticle = false
  }

  displayAddArticle(): void {
    this.showAllArticles = false
    this.showAddArticle = true
  }

  loadImage(event: any) {
    this.picture = event.target.files[0]
  }

  private normaliseForm(): void {
    this.title = ''
    this.text = ''
    this.errorMessage = ''
    this.formArticle.control.reset()
  }

  submitArticle(): void {
  
    this.adminService.saveArticle(new Article()
                                    .withTitle(this.title)
                                    .withContent(this.text),
                                  this.picture).subscribe(
      (data) => {
        this.errorMessage = ''
        this.normaliseForm()
        alert('saved article!')
      },
      (error) => {
        if (error['status'] == 400) {
          this.errorMessage = 'Incorect article information'
        } else if (error['status'] == 401) {
          this.router.navigate(['/multisport'])
        } else if (error['status'] == 500) {
          this.errorMessage = 'Internal server error'
        }
      }
      )

  }

}
