import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { AdventuresHttpService } from '../../services/http/adventures.http.service';
import { AdventureDTO, AdventureImageDTO } from '../../models/adventure-models';

@Component({
    selector: 'adv-adventure-detail-page',
    templateUrl: './adventure-detail-page.component.html',
    styleUrls: ['./adventure-detail-page.component.scss'],
})
export class AdventureDetailPageComponent implements OnInit, OnDestroy {
    private adventureChangeSubscription: Subscription;
    private interval;

    adventure: AdventureDTO;
    selectedImage: AdventureImageDTO;
    constructor(private httpService: AdventuresHttpService, private activeRoute: ActivatedRoute) {}

    ngOnInit(): void {
        this.adventureChangeSubscription = this.activeRoute.params.subscribe((routeParams) => {
            this.adventure = null;
            this.httpService
                .getAdventureById(+routeParams.adventureId)
                .toPromise()
                .then((adventure) => {
                    this.adventure = adventure;

                    clearInterval(this.interval);

                    const images = [...adventure.adventureImages];
                    images.push((this.selectedImage = images.shift()));
                    this.interval = setInterval(() => {
                        images.push((this.selectedImage = images.shift()));
                    }, 2500);
                });
        });
    }

    ngOnDestroy(): void {
        if (this.adventureChangeSubscription) {
            this.adventureChangeSubscription.unsubscribe();
        }
        clearInterval(this.interval);
    }
}
