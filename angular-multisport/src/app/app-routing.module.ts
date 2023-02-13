import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin/admin.component';
import { ArticleComponent } from './admin/article/article.component';
import { CentersComponent } from './admin/centers/centers.component';
import { PlanComponent } from './admin/plan/plan.component';
import { AdminSingleUserComponent } from './admin/user/admin-single-user/admin-single-user.component';
import { UserComponent } from './admin/user/user.component';
import { AdminGuard } from './guards/admin.guard';
import { HomeComponent } from './home/home.component';
import { SportCenter } from './models/centers/SportCenter';
import { NotFoundComponent } from './not-found/not-found.component';
import { ArticleInfoComponent } from './public/articles/article-info/article-info.component';
import { PublicArticlesComponent } from './public/articles/public-articles.component';
import { PublicRelaxCentersComponent } from './public/centers/relax/public-relax-centers.component';
import { SingleRelaxCenterComponent } from './public/centers/relax/single-relax-center/single-relax-center.component';
import { PublicSportCentersComponent } from './public/centers/sport/public-sport-centers.component';
import { SingleSportCenterComponent } from './public/centers/sport/single-sport-center/single-sport-center.component';
import { PlanInfoComponent } from './public/plan/plan-info/plan-info.component';
import { PublicPlansComponent } from './public/plan/public-plans.component';

const routes: Routes = [
  { path: "multisport", children: [
      { path: "", component: HomeComponent },
      { path: "not-found" , component: NotFoundComponent },
      { path: "articles", component: PublicArticlesComponent },
      { path: "articles/:title", component: ArticleInfoComponent },
      { path: "sport-centers", component: PublicSportCentersComponent },
      { path: "sport-centers/:id", component: SingleSportCenterComponent },
      { path: "relax-centers", component: PublicRelaxCentersComponent },
      { path: "relax-centers/:id", component: SingleRelaxCenterComponent },
      { path: "plans", component: PublicPlansComponent },
      { path: "plans/:name", component: PlanInfoComponent },
      { path: "**", pathMatch: 'full', component: NotFoundComponent }
  ] },
  { path: "multisport/admin", component: AdminComponent , canActivate: [AdminGuard], canActivateChild: [AdminGuard], children : [
    { path: "articles", component: ArticleComponent },
    { path: "users", component: UserComponent },
    { path: "users/:id", component: AdminSingleUserComponent },
    { path: "plans", component: PlanComponent },
    { path: "centers", component: CentersComponent },
    { path: "**", pathMatch: 'full', component: NotFoundComponent }
  ]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
