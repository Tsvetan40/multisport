import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { RelaxCenter } from 'src/app/models/centers/RelaxCenter';
import { SportCenter } from 'src/app/models/centers/SportCenter';
import { CenterType } from 'src/app/models/centers/typecenter/CenterType';
import { AdminService } from 'src/app/services/admin.service';

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

  constructor (private adminService: AdminService) {} 

  addPictureList(picture: HTMLInputElement) {
    if (!picture.value) {
      console.log('display error message')
      return
    }

    this.pictures.push(picture.value)
    
    picture.value = ''
    
  }

  private getServices(): string[] {
    const servicesList = this.services.split(',')
    return servicesList
  }

  submit(): void {
    if (this.typeCenter == 'Sport Center') {

        const sportCenter = {
                              'name': this.name, 
                              'address': this.address,
                              'pictures': this.pictures,
                              'description': this.description,
                              'centerType': 'RELAX_CENTER',
                              'services': this.services
                            } 
      this.adminService.saveSportCenter(sportCenter).subscribe()
    } else {
      const relaxCenter = {
                            'name': this.name, 
                            'address': this.address,
                            'pictures': this.pictures,
                            'description': this.description,
                            'centerType': 'RELAX_CENTER',
                            'services': this.getServices()
                          }
        this.adminService.saveRelaxCenter(relaxCenter).subscribe()
    }
  
    this.address = ''
    this.name = ''
    this.services = ''
    this.description = ''
    this.centerForm.control.reset()
    this.services = ''
    this.pictures = []
    this.centerForm.reset()
  }

  deletePicture(i: number) {
    this.pictures.splice(i, 1)
  }
}