import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Article } from 'src/app/models/page/Article';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'all-articles-admin',
  templateUrl: './all-articles.component.html',
  styleUrls: ['./all-articles.component.css']
})
export class AllArticlesComponent implements OnInit{
  isDeleted: number = 0
  message: string = ''
  articles: Article[] = []

  constructor(private adminService: AdminService, private router: Router) {}

  ngOnInit(): void {
    this.adminService.getAllArticlesTitle().subscribe(
      (data) => {
        debugger
        data.forEach(article => {
          this.articles.push(article)
        })
      },
      (error) => {
        if (error['status'] == 403) {
          this.router.navigate(['/multisport'])
        } else {

        }

      }
    )
  }

  deleteArticle(i: number): void {
    const title = this.articles[i].title
  
    this.adminService.deleteArticleByTitile(title).subscribe(
       (data) => {
        debugger
        this.articles.splice(i, 1)
        this.message = 'Article deleted successfylly!'
        this.isDeleted = 1
      }, 
      (error) => {
        debugger
        this.isDeleted = 2
        if (error['status'] == 403) {
          this.router.navigate(['/multisport'])
        } else {
          this.message = 'Cannot delete article'
        }

      }
    )
  }

}
