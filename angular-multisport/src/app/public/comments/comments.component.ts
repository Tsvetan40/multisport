import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
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

  public dateToString(comment: Comment) {
    console.log(comment.publishedAt)
    console.log(typeof (comment.publishedAt))
    const date = new Date(comment.publishedAt);
    return date.toDateString()
  }

  addComment() {
    this.addCommentEventEmitter.emit(this.commentContent)
    this.commentContent = ''
  }
}
