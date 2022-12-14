import { Component, Input, EventEmitter, Output } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-popup',
  templateUrl: './popup.component.html',
  styleUrls: ['./popup.component.css']
})
export class PopupComponent {
  @Input() btnPopup: string = '';
  @Output() eventEmitter = new EventEmitter<string>();

  private firstName: string = ''
  private secondName: string = '';
  private email: string = '';
  private password: string = '';
  private age: number = 0;

  constructor(loginService: LoginService) {}

  close(): void {
    this.btnPopup = ''
    this.eventEmitter.emit(this.btnPopup);
  }

  firstNameInput(event: any) {
    this.firstName = event.target.value;
  }

  secondNameInput(event: any) {
    this.secondName = event.target.value
  }

  emailInput(event: any) {
    this.email = event.target.value
  }

  passwordInput(event: any) {
    this.password = event.target.value
  }

  ageInput(event: any) {
    this.age = event.age.value
  }

  submit():void {
    console.log(this.firstName)
    console.log(this.secondName)
    console.log(this.email)
    console.log(this.password)
    console.log(this.age)
  }
}
