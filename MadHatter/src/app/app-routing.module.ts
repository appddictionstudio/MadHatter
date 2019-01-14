import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { LearningComponent } from './learning/learning.component';
import { LearningDetailsComponent } from './learning-details/learning-details.component';
import { VideosComponent } from './videos/videos.component';
import { MemberDirectoryComponent } from './member-directory/member-directory.component';
import { SignupComponent } from './signup/signup.component';

const routes: Routes = [
  { path: '', component: LoginComponent },
    // { path: '', children: [
    { path: 'home', component: HomeComponent },
    { path: `learning`, component: LearningComponent },
    { path: `learningdetail`, component: LearningDetailsComponent },
    { path: `videos`, component: VideosComponent },
    { path: 'memberdirectory', component: MemberDirectoryComponent},
    { path: 'signup', component: SignupComponent},
    { path: 'login', component: LoginComponent}
    // ]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
 }
