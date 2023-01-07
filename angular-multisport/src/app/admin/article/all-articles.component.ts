import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'all-articles-admin',
  templateUrl: './all-articles.component.html',
  styleUrls: ['./all-articles.component.css']
})
export class AllArticlesComponent implements OnInit{
  
  titles: string[] = []

  constructor(private adminService: AdminService, private router: Router) {}

  ngOnInit(): void {
    this.adminService.getAllArticlesTitle().subscribe(
      (data) => {
        data.forEach(title => {
          this.titles.push(title)
        })
      },
      (error) => {
        this.router.navigate(['/multisport'])
      }
    )
  }

  deleteArticle(i: number): void {
    const title = this.titles[i]
    this.adminService.deleteArticleByTitile(title).subscribe(
      data => {
        this.titles.splice(i, 1)
      }, 
      error => {
        this.router.navigate(['/multisport'])
      }
    )
  }

}
