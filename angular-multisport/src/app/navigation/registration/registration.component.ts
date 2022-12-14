import { Component, Input, Output, ViewChild, EventEmitter } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';
import { PatternService } from 'src/app/services/pattern.service';
import { NgForm  } from '@angular/forms';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent {
  @Input() btnPopup: string = '';
  @Output() eventEmitter = new EventEmitter<string>();
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
    this.btnPopup = ''
    this.eventEmitter.emit(this.btnPopup);
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

  }
}
