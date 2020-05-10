import { AdventureImageDTO } from './AdventureImageDTO';

export class AdventureLightDTO {
    id: number;
    name: string;
    startDate: Date;
    price: number;
    categoryId: string;
    image: AdventureImageDTO;

    constructor() {}
}
