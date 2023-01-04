import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { PatternService } from 'src/app/services/pattern.service';

@Component({
  selector: 'app-plan',
  templateUrl: './plan.component.html',
  styleUrls: ['./plan.component.css']
})
export class PlanComponent {
  nameCenter!: string
  name!: string
  price!: number
  centers: string[] = []
  isSubmitTouched: boolean = false
  @ViewChild('planForm') planForm!: NgForm

  constructor(private patternService: PatternService) {}

  disableAddCenterBtn(): boolean {
    return this.patternService.disableAddCenterBtn(this.nameCenter, this.planForm)
  }

  centerHasErrors(): boolean {
    return this.patternService.displayErrorCenter(this.nameCenter, this.planForm)
  }

  //to to not any but HtmlInputElement
  addCenter(): void {
    console.log('click')
    this.centers.push(this.nameCenter)
    this.nameCenter = ''
  }

  submitPlan():void {
    this.isSubmitTouched = true
        
  }
}
