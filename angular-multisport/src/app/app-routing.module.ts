import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin/admin.component';
import { ArticleComponent } from './admin/article/article.component';
import { CentersComponent } from './admin/centers/centers.component';
import { PlanComponent } from './admin/plan/plan.component';
import { UserComponent } from './admin/user/user.component';
import { AdminGuard } from './guards/admin.guard';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
  { path: "multisport", component: HomeComponent },
  { path: "multisport/admin", component: AdminComponent , canActivate: [AdminGuard], canActivateChild: [AdminGuard], children : [
    { path: "articles", component: ArticleComponent },
    { path: "users", component: UserComponent },
    { path: "plans", component: PlanComponent },
    { path: "centers", component: CentersComponent }
  ]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
