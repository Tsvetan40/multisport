import { Component } from '@angular/core';
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

  constructor(private adminService: AdminService, private router: Router) {}

  submitArticle(): void {
  
    this.adminService.saveArticle(new Article(this.title, this.text)).subscribe(
      (data) => {
        //to do
        this.errorMessage = ''
        alert('saved article!')
      },
      (error) => {
        if (error['status'] == 400) {
          this.errorMessage = "Article title already exists"
        } else if (error['status'] == 401) {
          this.router.navigate(['/multisport'])
        }
        console.log(error['status'])
      }
      )

  }

}
