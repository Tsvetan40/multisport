import { Injectable } from '@angular/core';
import { NgForm } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class PatternService {

  private regexNames: RegExp = new RegExp("^[a-zA-Z]*$")
  private regexPassword: RegExp = new RegExp(/^(?!.*\s)(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[~`!@#$%^&*()--+={}\[\]|\\:;"'<>,.?/_₹]).{10,16}$/)
  // private regexPlanCenter: RegExp = new RegExp(/^[A-Za-z]([A-Za-z0-9]+){3,}$/)
  private regexArticleAuthor: RegExp = new RegExp(/^[A-Z][a-z]{3,}\s[A-Z][a-z]{3,}$/)
  constructor() {
  
  }

  hasErrorName(name: string): boolean {
    return !this.regexNames.test(name);
  }

  hasErrorPassword(password: string): boolean {
    return !this.regexPassword.test(password);
  }

  disableAddCenterBtn(center: string, form: NgForm): boolean {
    if (center == null) {
      return true
    }
    if ((center?.length < 4 || center?.length > 20) )
      return true
    
    return false
  }

  displayErrorCenter(center: string, form: NgForm): boolean {
    if ( form?.dirty && center == '' ) {
      return false //doesn't have error, but disable button for adding center
    }
    
    return center?.length < 4 || center?.length > 20 //has error and disable buttton for adding center
  }

  hasArticleAuthorErrorFormat(author: string): boolean {
    return this.regexArticleAuthor.test(author)
  }
}
