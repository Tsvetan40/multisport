import { Component } from '@angular/core';
import { PatternService } from 'src/app/services/pattern.service';

@Component({
  selector: 'app-article',
  templateUrl: './article.component.html',
  styleUrls: ['./article.component.css']
})
export class ArticleComponent {
  title!: string
  author!: string
  text!: string

  constructor(private patternService: PatternService) {}

  authorHasErrors(): boolean {
    return !this.patternService.hasArticleAuthorErrorFormat(this.author)
  }

  submitArticle(): void {
    //check this validation for later and in other components
    if ( this.authorHasErrors() ) {
      return
    }

  }
}
