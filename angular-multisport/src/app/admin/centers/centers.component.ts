import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-centers',
  templateUrl: './centers.component.html',
  styleUrls: ['./centers.component.css']
})
export class CentersComponent {
  name!: string
  address!: string
  description!: string
  services!: string 
  typeCenter: string = 'Sport Center'
  pictures: string[] = []
  @ViewChild('centerForm') centerForm!: NgForm

  addPictureList(picture: HTMLInputElement) {
    if (!picture.value) {
      console.log('display error message')
      return
    }

    this.pictures.push(picture.value)
    
    picture.value = ''
    
  }

  submit(): void {
    this.address = ''
    this.name = ''
    this.services = ''
    this.description = ''
    //this.centerForm.reset()
  }

  deletePicture(i: number) {
    this.pictures.splice(i, 1)
  }
}