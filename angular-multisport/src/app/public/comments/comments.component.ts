import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Comment } from 'src/app/models/page/Comment';

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent {
  commentContent: string = ''
  @Output() addCommentEventEmitter = new EventEmitter<string>()
  @Input() comments: Comment[]

  constructor() {
    this.comments = []
  }


  addComment() {
    this.addCommentEventEmitter.emit(this.commentContent)
  }
}
