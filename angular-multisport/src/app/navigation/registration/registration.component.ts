import { Component, Input, Output, ViewChild, EventEmitter } from '@angular/core';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { PatternService } from 'src/app/services/pattern.service';
import { NgForm  } from '@angular/forms';
import { RegisteredUser } from 'src/app/models/user/RegisteredUser';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent {
  @Input() btnRegistrationPopup: string = '';
  @Output() registrationEventEmitter = new EventEmitter<string>();
  @Output() isAdminEventEmitter = new EventEmitter<boolean>()
  @ViewChild('myForm') form!: NgForm

  hasAuthenticationError: boolean = false
  firstName: string = '';
  secondName: string = ''
  password: string = '';
  email: string = '';
  age!: number;

  ngOnInit(): void {
  }

  constructor(private authService: AuthenticationService,private patternService: PatternService) {}

  close(): void {
    this.btnRegistrationPopup = ''
    this.registrationEventEmitter.emit(this.btnRegistrationPopup);
  }

  hasErrorName():boolean {
    return this.patternService.hasErrorName(this.firstName)
  }

  hasErrorSecondName(): boolean {
    return this.patternService.hasErrorName(this.secondName)
  }

  hasErrorPassword(): boolean {
    return this.patternService.hasErrorPassword(this.password)
  }

  onSubmit() {
    const user  = new RegisteredUser(this.email, this.password, this.firstName, this.secondName, this.age)
    
    this.authService.registartion(user).subscribe(
      data => {
        
        if (data == null) {
          this.hasAuthenticationError = true
          return
        } else if (data['email'].includes('@multisport.com')) {
          this.isAdminEventEmitter.emit(true)
        } else {
          this.isAdminEventEmitter.emit(false)
        }

        this.hasAuthenticationError = false
        this.close()
      }
    );
    
  }
}
