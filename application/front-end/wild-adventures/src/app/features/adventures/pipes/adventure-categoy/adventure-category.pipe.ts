import { Pipe, PipeTransform } from '@angular/core';
import { AdventureLightDTO } from '../../models/adventure-models';

@Pipe({
    name: 'adventureCategory',
})
export class AdventureCategoryPipe implements PipeTransform {
    transform(adventuresList: AdventureLightDTO[], category: string): unknown {
        if (category) {
            return adventuresList.filter((adventure) => adventure.categoryId === category);
        }
        return adventuresList;
    }
}
