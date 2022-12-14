import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavigationComponent } from './navigation/navigation.component';
import { HomeComponent } from './home/home.component';
import { LoginService } from './services/login.service';
import { PatternService } from './services/pattern.service';
import { FormsModule } from '@angular/forms';
import { RegistrationComponent } from './navigation/registration/registration.component';
import { LoginComponent } from './navigation/login/login.component';

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    HomeComponent,
    RegistrationComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [LoginService, PatternService],
  bootstrap: [AppComponent]
})
export class AppModule { }
