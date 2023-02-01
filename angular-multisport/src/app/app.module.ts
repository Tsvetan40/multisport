import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { HttpClientModule } from '@angular/common/http';
import { NavigationModule } from './navigation/navigation.module';
import { AdminModule } from './admin/admin.module';
import { PublicSportCentersComponent } from './public/centers/sport/public-sport-centers.component';
import { PublicRelaxCentersComponent } from './public/centers/relax/public-relax-centers.component';
import { PublicPlansComponent } from './public/plan/public-plans.component';
import { PublicArticlesComponent } from './public/articles/public-articles.component';
import { PublicService } from './services/public.service';
import { PlanInfoComponent } from './public/plan/plan-info/plan-info.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    PublicSportCentersComponent,
    PublicRelaxCentersComponent,
    PublicPlansComponent,
    PublicArticlesComponent,
    PlanInfoComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NavigationModule,
    AdminModule
  ],
  providers: [PublicService],
  bootstrap: [AppComponent]
})
export class AppModule { }
