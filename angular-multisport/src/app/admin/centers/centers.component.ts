import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
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

  constructor (private adminService: AdminService, private router: Router) {} 

  addPictureList(picture: HTMLInputElement) {
    if (!picture.value) {
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
      this.adminService.saveSportCenter(sportCenter).subscribe(
        data => {
          alert("Center Saved!")
        },
        error => {
          if (error['status'] == 403) {
            this.router.navigate(["/multisport"])
          } else {
            alert("Center Not Saved")
          }
        }

      )
    } else {
      const relaxCenter = {
                            'name': this.name, 
                            'address': this.address,
                            'pictures': this.pictures,
                            'description': this.description,
                            'centerType': 'RELAX_CENTER',
                            'services': this.getServices()
                          }
        this.adminService.saveRelaxCenter(relaxCenter).subscribe(
          data => {
            alert("Center Saved!")
          },
          error => {
            if (error['status'] == 403) {
              this.router.navigate(["/multisport"])
            } else {
              alert("Center Not Saved")
            }
          }
        )
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