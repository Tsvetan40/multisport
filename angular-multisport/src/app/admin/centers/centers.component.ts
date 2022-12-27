import { Component } from '@angular/core';

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
  }

  deletePicture(i: number) {
    console.log(typeof i)
    this.pictures.splice(i, 1)
  }
}