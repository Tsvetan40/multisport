import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent {
  commentContent: string = ''
  @Output() addCommentEventEmitter = new EventEmitter<string>()


  addComment() {
    this.addCommentEventEmitter.emit(this.commentContent)
  }
}
