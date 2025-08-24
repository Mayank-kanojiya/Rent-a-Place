import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { AddpropertyComponent } from './addproperty/addproperty.component';
import { OwnersignupComponent } from './ownersignup/ownersignup.component';
import { SignupComponent } from './signup/signup.component';
import { OwnerdashboardComponent } from './ownerdashboard/ownerdashboard.component';
import { UserdashboardComponent } from './userdashboard/userdashboard.component';
import { PropertyComponent } from './property/property.component';
import { EmailsComponent } from './emails/emails.component';
import { MessagesComponent } from './messages/messages.component';
import { UserbookingsComponent } from './userbookings/userbookings.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'ownersignup', component: OwnersignupComponent },
  { path: 'ownerdashboard', component: OwnerdashboardComponent },
  { path: 'addproperty', component: AddpropertyComponent },
  { path: 'userdashboard', component: UserdashboardComponent },
  { path: 'property/:id', component: PropertyComponent },      // <--- FIXED
  { path: 'messages', component: MessagesComponent },
  { path: 'email', component: EmailsComponent },
   { path: 'userbookings', component: UserbookingsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
