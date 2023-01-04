import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { NavigationComponent } from './navigation.component';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { PatternService } from '../services/pattern.service';
import { LoginService } from '../services/login.service';



@NgModule({
  declarations: [
    LoginComponent,
    RegistrationComponent,
    NavigationComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule
  ],
  exports: [
    LoginComponent, 
    RegistrationComponent,
    NavigationComponent,
    FormsModule,
    CommonModule,
    RouterModule
  ],
  providers: [LoginService, PatternService]
})
export class NavigationModule { }
