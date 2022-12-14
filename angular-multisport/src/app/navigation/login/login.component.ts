import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  @Input() btnLoginPopup: string = '';
  @Output() loginEventEmitter = new EventEmitter<string>()
  
  close(): void {
    this.btnLoginPopup = ''
    this.loginEventEmitter.emit('')
  }
}
