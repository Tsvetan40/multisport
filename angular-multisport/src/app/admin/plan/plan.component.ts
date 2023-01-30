import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Plan } from 'src/app/models/Plan';
import { AdminService } from 'src/app/services/admin.service';
import { PatternService } from 'src/app/services/pattern.service';

@Component({
  selector: 'app-plan',
  templateUrl: './plan.component.html',
  styleUrls: ['./plan.component.css']
})
export class PlanComponent {
  addressCenter!: string
  name!: string
  price!: number
  picture!: File
  centersAddresses: string[] = []
  isSubmitTouched: boolean = false
  @ViewChild('planForm') planForm!: NgForm

  constructor(private patternService: PatternService, private adminService: AdminService) {}

  disableAddCenterBtn(): boolean {
    return this.patternService.disableAddCenterBtn(this.addressCenter, this.planForm)
  }

  centerHasErrors(): boolean {
    return this.patternService.displayErrorCenterAddress(this.addressCenter, this.planForm)
  }

  //to to not any but HtmlInputElement
  addCenter(): void {
    this.centersAddresses.push(this.addressCenter)
    this.addressCenter = ''
  }

  loadPicture(event: any) {
    this.picture = event.target.files[0]
  }

  submitPlan():void {
    this.isSubmitTouched = true
    const plan = new Plan(this.name, this.price, this.centersAddresses)
    debugger
    this.adminService.savePlan(plan, this.picture).subscribe() 
    this.centersAddresses = []
    this.planForm.control.reset()
  }
}
