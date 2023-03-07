import { Component, EventEmitter, Input, Output } from '@angular/core';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { PatternService } from 'src/app/services/pattern.service';
import { LoggedUser } from 'src/app/models/user/LoggedUser';
import { Role } from 'src/app/models/user/Role';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  @Input() btnLoginPopup: string = '';
  @Output() loginEventEmitter = new EventEmitter<string>()
  @Output() isAdminEventEmitter = new EventEmitter<boolean>()

  hasLoginError: boolean = false
  password!: string;
  email!: string;
  errMessage: string  = '';
  constructor(private patternService: PatternService, private loginService: AuthenticationService) {}

  close(): void {
    this.btnLoginPopup = ''
    this.loginEventEmitter.emit('')
  }

  hasErrorPassword(): boolean {
    return this.patternService.hasErrorPassword(this.password)
  }

  onSubmit():void {
    const loggedUser = new LoggedUser(this.email, this.password)

    this.loginService.login(loggedUser).subscribe(
      data => {
        if (data['role'] == Role.ADMIN) {
          this.loginService.setIsAdmin(true)
          this.isAdminEventEmitter.emit(this.loginService.checkCredetentials())
        } else {
          this.loginService.setIsAdmin(false)
          this.isAdminEventEmitter.emit(this.loginService.checkCredetentials())
        }
        this.hasLoginError = false
        this.close()
      },
      err => {
        this.hasLoginError = true
      }
    )
    
  }
}
