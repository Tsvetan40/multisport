import { Component, EventEmitter, Input, Output } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';
import { PatternService } from 'src/app/services/pattern.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  @Input() btnLoginPopup: string = '';
  @Output() loginEventEmitter = new EventEmitter<string>()
  
  password!: string;
  email!: string;

  constructor(private patternService: PatternService, private LoginService: LoginService) {}

  close(): void {
    this.btnLoginPopup = ''
    this.loginEventEmitter.emit('')
  }

  hasErrorPassword(): boolean {
    return this.patternService.hasErrorPassword(this.password)
  }

  onSubmit():void {
    console.log(this.password + " " + this.email)
    this.LoginService.login(this.email, this.password).subscribe(
      user => console.log("hello " + user)
    )

  }
}
