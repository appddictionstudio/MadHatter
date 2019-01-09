import { Pipe, PipeTransform } from '@angular/core';
import { Users } from '../models/Users';

@Pipe({ name: 'filter' })
export class SearchByNamePipe implements PipeTransform {
  transform(items: any[], field: string, value: string): any[] {
    if (!items) {
        return [];
    }
    if (!field || !value) {
        return items;
    }

    return items.filter(singleItem =>
        singleItem[field].toLowerCase().includes(value.toLowerCase())
    );
}
}
