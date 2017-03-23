import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GatewaySharedModule } from '../../shared';

import {
    DemoService,
    DemoPopupService,
    DemoComponent,
    DemoDetailComponent,
    DemoDialogComponent,
    DemoPopupComponent,
    DemoDeletePopupComponent,
    DemoDeleteDialogComponent,
    demoRoute,
    demoPopupRoute,
} from './';

let ENTITY_STATES = [
    ...demoRoute,
    ...demoPopupRoute,
];

@NgModule({
    imports: [
        GatewaySharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        DemoComponent,
        DemoDetailComponent,
        DemoDialogComponent,
        DemoDeleteDialogComponent,
        DemoPopupComponent,
        DemoDeletePopupComponent,
    ],
    entryComponents: [
        DemoComponent,
        DemoDialogComponent,
        DemoPopupComponent,
        DemoDeleteDialogComponent,
        DemoDeletePopupComponent,
    ],
    providers: [
        DemoService,
        DemoPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GatewayDemoModule {}
