import { Component, OnInit, OnDestroy } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable, Subscription } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';

import { ReservationDTO } from 'src/app/shared/models/dto/reservations';
import { KeycloakService } from 'src/app/shared/services/keycloak/keycloak.service';
import { CartContentService } from 'src/app/shared/services/cart-content/cart-content.service';

@Component({
    selector: 'adv-navigation',
    templateUrl: './navigation.component.html',
    styleUrls: ['./navigation.component.scss'],
})
export class NavigationComponent implements OnInit, OnDestroy {
    public isHamburgerMode: Observable<boolean>;
    public isLoggedIn = false;
    public reservationNb = 0;
    private reservationSub: Subscription;

    constructor(
        private breakpointObserver: BreakpointObserver,
        private keycloakService: KeycloakService,
        private cartcontentService: CartContentService
    ) {}

    get userName() {
        return this.keycloakService.getUsername();
    }

    get isKeycloakUp() {
        return this.keycloakService.isServerUp;
    }

    login() {
        if (this.keycloakService.isServerUp) {
            this.keycloakService.login();
        }
    }

    logout() {
        if (this.keycloakService.isServerUp) {
            this.keycloakService.logout();
        }
    }

    account() {
        if (this.keycloakService.isServerUp) {
            this.keycloakService.getKeycloakInstance().accountManagement();
        }
    }

    ngOnInit(): void {
        this.isHamburgerMode = this.breakpointObserver
            .observe([Breakpoints.XSmall, Breakpoints.Small, Breakpoints.Handset])
            .pipe(
                map((result) => result.matches),
                shareReplay()
            );

        if (this.keycloakService.isServerUp) {
            this.keycloakService.isLoggedIn().then((isLoggedIn) => {
                Object.assign(this, { isLoggedIn });
            });
        }
        this.reservationNb = this.cartcontentService.reservations.length;
        this.reservationSub = this.cartcontentService.reservationsUpdated.subscribe((reservations) => {
            this.reservationNb = reservations.length;
        });
    }

    ngOnDestroy() {
        this.reservationSub.unsubscribe();
    }
}
