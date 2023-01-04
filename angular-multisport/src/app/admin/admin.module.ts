import { NgModule } from '@angular/core';
import { AdminComponent } from './admin.component';
import { AdminNavigationComponent } from './admin-navigation/admin-navigation.component';
import { ArticleComponent } from './article/article.component';
import { CentersComponent } from './centers/centers.component';
import { PlanComponent } from './plan/plan.component';
import { UserComponent } from './user/user.component';
import { NavigationModule } from '../navigation/navigation.module';



@NgModule({
  declarations: [
    AdminComponent,
    AdminNavigationComponent,
    ArticleComponent,
    CentersComponent,
    PlanComponent,
    UserComponent
  ],
  imports: [
    NavigationModule
  ]
})
export class AdminModule { }
