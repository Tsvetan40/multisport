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
import { HttpClientModule } from '@angular/common/http';
import { AdminComponent } from './admin/admin.component';
import { ArticleComponent } from './admin/article/article.component';
import { UserComponent } from './admin/user/user.component';
import { PlanComponent } from './admin/plan/plan.component';
import { CentersComponent } from './admin/centers/centers.component';

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    HomeComponent,
    RegistrationComponent,
    LoginComponent,
    AdminComponent,
    ArticleComponent,
    UserComponent,
    PlanComponent,
    CentersComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    BrowserModule,
    HttpClientModule
  ],
  providers: [LoginService, PatternService],
  bootstrap: [AppComponent]
})
export class AppModule { }
