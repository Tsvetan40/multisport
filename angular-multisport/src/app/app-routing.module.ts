import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin/admin.component';
import { ArticleComponent } from './admin/article/article.component';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
  { path: "multisport", component: HomeComponent },
  { path: "multisport/admin", component: AdminComponent , children : [
    { path: "articles", component: ArticleComponent }
  ]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
