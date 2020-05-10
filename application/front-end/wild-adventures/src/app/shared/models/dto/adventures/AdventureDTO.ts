import { AdventureImageDTO } from './AdventureImageDTO';

export class AdventureDTO {
    id: number;
    name: string;
    description: string;
    startDate: Date;
    price: number;
    maxParticipantNumber: number;
    categoryId: string;
    adventureImages: AdventureImageDTO[];

    constructor() {}
}
