import { Component, HostListener, Input, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { Role } from '../models/user/Role';
import { AuthenticationService } from '../services/authentication.service';

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

  constructor(private authService: AuthenticationService, private router: Router) {}

  ngOnInit(): void {
    this.isAdmin =  this.authService.checkCredetentials()
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

  logout(): void {
    this.isAdmin = false
    this.authService.setIsAdmin(false)
    this.router.navigateByUrl('/multisport')
  }

}