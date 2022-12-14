import { Component, Input, EventEmitter, Output, OnInit, ViewChild } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';
import { PatternService } from 'src/app/services/pattern.service';
import { NgForm } from '@angular/forms'

@Component({
  selector: 'app-popup',
  templateUrl: './popup.component.html',
  styleUrls: ['./popup.component.css']
})
export class PopupComponent implements OnInit{
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
