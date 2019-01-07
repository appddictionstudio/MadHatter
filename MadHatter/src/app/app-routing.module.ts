import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { LearningComponent } from './learning/learning.component';
import { VideosComponent } from './videos/videos.component';
const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: '', children: [
    { path: 'home', component: HomeComponent },
    { path: `learning`, component: LearningComponent },
    { path: `videos`, component: VideosComponent },
  ]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
 }
