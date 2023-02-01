import { Component, OnInit } from '@angular/core';
import { PublicService } from 'src/app/services/public.service';

@Component({
  selector: 'app-public-articles',
  templateUrl: './public-articles.component.html',
  styleUrls: ['./public-articles.component.css']
})
export class PublicArticlesComponent implements OnInit{
  
  constructor(private publicService: PublicService) { }

  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }
  
}
