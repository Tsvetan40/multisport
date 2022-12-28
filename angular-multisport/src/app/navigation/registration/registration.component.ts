import { Component, Input, Output, ViewChild, EventEmitter } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';
import { PatternService } from 'src/app/services/pattern.service';
import { NgForm  } from '@angular/forms';
import { User } from 'src/app/models/User';

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


  firstName: string = '';
  secondName: string = ''
  password: string = '';
  email: string = '';
  age!: number;

  ngOnInit(): void {
  }

  constructor(private loginService: LoginService,private patternService: PatternService) {}

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
   console.log(this.form.valid)
    const user  = new User(this.firstName, this.secondName, this.email, this.password, this.age)
    //this.loginService.registartion(newUser).subscribe();
    //after successful registartion
    if (this.email.includes('@multisport.com')) {
      this.isAdminEventEmitter.emit(true)
    } else {
      this.isAdminEventEmitter.emit(false)
    }

  }
}
