import { Component, OnInit } from '@angular/core';
import { AdventureLightDTO } from '../../models/adventure-models';
import { AdventuresHttpService } from '../../services/http/adventures.http.service';

@Component({
    selector: 'adv-adventures-list-page',
    templateUrl: './adventures-list-page.component.html',
    styleUrls: ['./adventures-list-page.component.scss'],
})
export class AdventuresListPageComponent implements OnInit {
    adventures: AdventureLightDTO[];
    categories: string[];
    selectedCategory: string;

    constructor(private httpService: AdventuresHttpService) {}

    onChangeCategory(category: { value: string }) {
        this.selectedCategory = category.value;
    }

    ngOnInit(): void {
        this.httpService
            .getAdventureList()
            .toPromise()
            .then((adventures) => {
                this.adventures = adventures;
                this.categories = adventures
                    .map((adventure) => adventure.categoryId)
                    .filter((category, index, categories) => categories.indexOf(category) === index)
                    .sort();
            });
    }
}
