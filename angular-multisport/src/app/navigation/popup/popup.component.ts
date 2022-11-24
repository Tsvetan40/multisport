import { Component, Input, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-popup',
  templateUrl: './popup.component.html',
  styleUrls: ['./popup.component.css']
})
export class PopupComponent {
  @Input() btnPopup:string = '';
  @Output() eventEmitter = new EventEmitter<string>();

  close(): void {
    this.btnPopup = ''
    this.eventEmitter.emit(this.btnPopup);
  }
}
