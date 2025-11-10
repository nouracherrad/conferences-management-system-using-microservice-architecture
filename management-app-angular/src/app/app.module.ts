import {APP_INITIALIZER, NgModule} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ConferencesComponent } from './ui/conferences/conferences.component';
import { KeynotesComponent } from './ui/keynotes/keynotes.component';
import {HttpClientModule} from '@angular/common/http';
import {CommonModule} from '@angular/common';
import {KeycloakAngularModule, KeycloakService} from 'keycloak-angular';


function initializeKeycloak(keycloak: KeycloakService) {
  return () =>
    keycloak.init({
      config: {
        url: 'http://localhost:8080',
        realm: 'conference-realm',
        clientId: 'conference-client-angular'
      },
      initOptions: {
        onLoad: 'check-sso',
        silentCheckSsoRedirectUri:
          window.location.origin + '/assets/silent-check-sso.html'
}
    });
}
@NgModule({
  declarations: [
    AppComponent,
    KeynotesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    CommonModule,KeycloakAngularModule
  ],
  providers: [
    {provide:APP_INITIALIZER, useFactory : initializeKeycloak, multi:true ,deps:[KeycloakService]}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
