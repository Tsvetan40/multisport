import { Component, EventEmitter, Input, Output } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';
import { PatternService } from 'src/app/services/pattern.service';
import { LoggedUser } from 'src/app/models/LoggedUser';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  @Input() btnLoginPopup: string = '';
  @Output() loginEventEmitter = new EventEmitter<string>()
  @Output() isAdminEventEmitter = new EventEmitter<boolean>()

  password!: string;
  email!: string;
  errMessage: string  = '';
  constructor(private patternService: PatternService, private LoginService: LoginService) {}

  close(): void {
    this.btnLoginPopup = ''
    this.loginEventEmitter.emit('')
  }

  hasErrorPassword(): boolean {
    return this.patternService.hasErrorPassword(this.password)
  }

  onSubmit():void {
    const loggedUser = new LoggedUser(this.email, this.password)
    this.LoginService.login(loggedUser).subscribe(
      data => {
        if (data == null) {
          return
        }
        debugger
        console.log(data)
        if (data['email'].includes('@multisport.com')) {
          this.isAdminEventEmitter.emit(true)
        } else {
          this.isAdminEventEmitter.emit(false)
        }

        this.close()
      }
    )
    
  }
}
