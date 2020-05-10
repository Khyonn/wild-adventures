import { Component, OnInit, Input } from '@angular/core';
import { AdventureLightDTO } from '../../models/adventure-models';

@Component({
    selector: 'adv-adventure-card',
    templateUrl: './adventure-card.component.html',
    styleUrls: ['./adventure-card.component.scss'],
})
export class AdventureCardComponent implements OnInit {
    @Input() adventure: AdventureLightDTO;

    constructor() {}

    ngOnInit(): void {}
}
