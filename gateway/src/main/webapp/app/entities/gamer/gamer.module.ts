import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GatewaySharedModule } from '../../shared';

import {
    GamerService,
    GamerPopupService,
    GamerComponent,
    GamerDetailComponent,
    GamerDialogComponent,
    GamerPopupComponent,
    GamerDeletePopupComponent,
    GamerDeleteDialogComponent,
    gamerRoute,
    gamerPopupRoute,
} from './';

let ENTITY_STATES = [
    ...gamerRoute,
    ...gamerPopupRoute,
];

@NgModule({
    imports: [
        GatewaySharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        GamerComponent,
        GamerDetailComponent,
        GamerDialogComponent,
        GamerDeleteDialogComponent,
        GamerPopupComponent,
        GamerDeletePopupComponent,
    ],
    entryComponents: [
        GamerComponent,
        GamerDialogComponent,
        GamerPopupComponent,
        GamerDeleteDialogComponent,
        GamerDeletePopupComponent,
    ],
    providers: [
        GamerService,
        GamerPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GatewayGamerModule {}
