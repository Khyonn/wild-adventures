import { BrowserModule } from '@angular/platform-browser';
import { NgModule, DEFAULT_CURRENCY_CODE, LOCALE_ID, APP_INITIALIZER } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';

// Configuration de l'authentification
import { KeycloakAngularModule, KeycloakBearerInterceptor } from 'keycloak-angular';
import { TokenInterceptor } from './shared/interceptors/token.interceptor';

import { StripeModule } from 'stripe-angular';

// Pour enregistrer les locales
import { registerLocaleData } from '@angular/common';
import localeFr from '@angular/common/locales/fr';
import localeFrExtra from '@angular/common/locales/extra/fr';

import { SharedModule } from './shared/shared.module';
// Routing
import { AppRoutingModule } from './app-routing.module';
// Composant
import { AppComponent } from './app.component';
// Module de composants graphiques (menu / navigation)
import { LayoutModule as AppLayoutModule } from './shared/modules/layout/layout.module';
import { AuthInitializer } from './configs/auth/auth-initializer.service';
import { environment } from 'src/environments/environment';
import { HTTP_INTERCEPTORS } from '@angular/common/http';

@NgModule({
    declarations: [AppComponent],
    imports: [
        AppRoutingModule,
        BrowserAnimationsModule,
        BrowserModule,
        AppLayoutModule,
        SharedModule,
        KeycloakAngularModule,
        StripeModule.forRoot(environment.stripe_key),
    ],
    providers: [
        { provide: LocationStrategy, useClass: HashLocationStrategy },
        { provide: DEFAULT_CURRENCY_CODE, useValue: 'EUR' },
        {
            provide: LOCALE_ID,
            useValue: 'fr-FR',
        },
        {
            provide: APP_INITIALIZER,
            useFactory(authInitializer: AuthInitializer) {
                return () => authInitializer.init();
            },
            deps: [AuthInitializer],
            multi: true,
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: TokenInterceptor,
            multi: true,
        },
    ],
    bootstrap: [AppComponent],
})
export class AppModule {}

registerLocaleData(localeFr, 'fr-FR', localeFrExtra);
