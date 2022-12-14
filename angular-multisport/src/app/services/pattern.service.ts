import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PatternService {

  private regexNames: RegExp = new RegExp("^[a-zA-Z]*$")
  private regexPassword: RegExp = new RegExp(/^(?!.*\s)(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[~`!@#$%^&*()--+={}\[\]|\\:;"'<>,.?/_₹]).{10,16}$/)

  
  constructor() {
  
  }

  hasErrorName(name: string): boolean {
    return !this.regexNames.test(name);
  }

  hasErrorPassword(password: string): boolean {
    return !this.regexPassword.test(password);
  }

}
