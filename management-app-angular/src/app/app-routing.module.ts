import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ConferencesComponent} from './ui/conferences/conferences.component';
import {KeynotesComponent} from './ui/keynotes/keynotes.component';
import {AuthGuard} from './guards/auth.guard';
const routes: Routes = [
  { path: 'conferences', component: ConferencesComponent, canActivate : [AuthGuard], data : {roles :['ADMIN']}},
  { path: 'keynotes', component: KeynotesComponent , canActivate : [AuthGuard], data : {roles : ['USER']}},
  { path: '', redirectTo: '/conferences', pathMatch: 'full' }
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
