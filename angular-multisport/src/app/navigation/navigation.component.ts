import { Component, HostListener, Input, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../services/login.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})

export class NavigationComponent implements OnInit{
  @Input() isAdmin = false
  public screenWidth!: number
  public isClicked!: boolean
  public show!: boolean
  public popupBtn: string = ''
  private readonly phoneWidth: number = 400

  constructor(private router: Router, private loginService: LoginService) {}

  ngOnInit(): void {
    this.screenWidth = window.innerWidth
   
    if (this.screenWidth > this.phoneWidth) {
      this.show = this.isClicked = true
      return
    }
    
    this.show = this.isClicked = false
  }

  chacgeClicked(): void {
    this.isClicked = !this.isClicked
    this.show = !this.show
  }

    @HostListener('window:resize')
    onResize(): void {
    this.screenWidth = window.innerWidth;
    if (this.screenWidth > this.phoneWidth) {
      this.isClicked = false;
      this.show = true;
      return;
    }

    if (this.isClicked) {
      this.show = true;
      return;
    }

    this.show = false;
  }

  setLogin(): void {
    this.popupBtn = "Login"
  }
  
  setSingIn(): void {
    this.popupBtn = "Sing in"
  }

  normalise(event: string): void {
    this.popupBtn = ''
  }

  setUserRole(event: boolean): void {
    this.isAdmin = event
  }

  normaliseLogin(event: string): void {
    this.popupBtn = ''
  }

  goAdmin():void {
    //now just navigate later check from backend
    this.loginService.manageUsers().subscribe(
      data => {
        debugger
        if (data == null) {
          return
        }
        this.router.navigateByUrl("multisport/admin")
      }
    )

  }
}