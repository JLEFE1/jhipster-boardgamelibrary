import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { GAMER_ROUTE, GamerComponent } from './';
import {GatewaySharedModule} from '../../shared/shared.module';

let ENTITY_STATES = [
    GAMER_ROUTE/*,
    ...GamerPopupRoute*/
];

@NgModule({
    imports: [
        GatewaySharedModule,
        RouterModule.forRoot(ENTITY_STATES, {useHash: true})
    ],
    declarations: [
        GamerComponent
    ],
    entryComponents: [

    ],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GatewayGamerModule {
}
