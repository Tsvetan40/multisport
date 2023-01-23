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
  centersAddresses: string[] = []
  isSubmitTouched: boolean = false
  @ViewChild('planForm') planForm!: NgForm

  constructor(private patternService: PatternService, private adminService: AdminService) {}

  disableAddCenterBtn(): boolean {
    return this.patternService.disableAddCenterBtn(this.addressCenter, this.planForm)
  }

  centerHasErrors(): boolean {
    console.log()
    return this.patternService.displayErrorCenterAddress(this.addressCenter, this.planForm)
  }

  //to to not any but HtmlInputElement
  addCenter(): void {
    console.log('click')
    this.centersAddresses.push(this.addressCenter)
    this.addressCenter = ''
  }

  submitPlan():void {
    this.isSubmitTouched = true
    const plan = new Plan(this.name, this.price, this.centersAddresses)
    this.adminService.savePlan(plan).subscribe() 
    this.centersAddresses = []
    this.planForm.control.reset()
  }
}
