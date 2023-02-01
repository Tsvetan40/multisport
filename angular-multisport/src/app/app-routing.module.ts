import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin/admin.component';
import { ArticleComponent } from './admin/article/article.component';
import { CentersComponent } from './admin/centers/centers.component';
import { PlanComponent } from './admin/plan/plan.component';
import { UserComponent } from './admin/user/user.component';
import { AdminGuard } from './guards/admin.guard';
import { HomeComponent } from './home/home.component';
import { PublicArticlesComponent } from './public/articles/public-articles.component';
import { PublicRelaxCentersComponent } from './public/centers/relax/public-relax-centers.component';
import { PublicSportCentersComponent } from './public/centers/sport/public-sport-centers.component';
import { PlanInfoComponent } from './public/plan/plan-info/plan-info.component';
import { PublicPlansComponent } from './public/plan/public-plans.component';

const routes: Routes = [
  { path: "multisport", children: [
      { path: "", component: HomeComponent },
      { path: "articles", component: PublicArticlesComponent },
      { path: "sport-centers", component: PublicSportCentersComponent },
      { path: "relax-centers", component: PublicRelaxCentersComponent },
      { path: "plans", component: PublicPlansComponent },
      { path: "plans/:name", component: PlanInfoComponent },
  ] },
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
