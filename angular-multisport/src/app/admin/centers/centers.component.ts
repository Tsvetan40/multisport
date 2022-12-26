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
  pictures: string[] = []

  addPictureList(picture: string) {
    if (!picture) {
      console.log('display error message')
      return
    }

    this.pictures.push(picture)
  }

  deletePicture(i: number) {
    console.log(typeof i)
    this.pictures.splice(i, 1)
  }
}