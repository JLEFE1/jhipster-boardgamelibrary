import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { GatewayDemoModule } from './demo/demo.module';
import { GatewayGamerModule } from './gamer/gamer.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        GatewayDemoModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
        GatewayGamerModule
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GatewayEntityModule {}
