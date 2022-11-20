import { Component, HostListener, OnInit, Renderer2 } from '@angular/core';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})

export class NavigationComponent implements OnInit{
  public screenWidth!: number;
  public isClicked!: boolean;
  public show!: boolean;

  ngOnInit(): void {
    this.screenWidth = window.innerWidth;
   
    if (this.screenWidth > 400) {
      this.show = this.isClicked = true;
      return;
    }
    
    this.show = this.isClicked = false;
  }

  chacgeClicked(): void {
    this.isClicked = !this.isClicked;
    this.show = !this.show;
  }

    @HostListener('window:resize')
    onResize(): void {
    this.screenWidth = window.innerWidth;
    if (this.screenWidth > 400) {
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
}