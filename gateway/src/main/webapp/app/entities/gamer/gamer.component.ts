import { Component, OnInit, OnDestroy } from '@angular/core';
import { Response } from '@angular/http';
import { Subscription } from 'rxjs/Rx';
import { EventManager, JhiLanguageService, AlertService } from 'ng-jhipster';

import { Gamer } from './gamer.model';
import { GamerService } from './gamer.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-gamer',
    templateUrl: './gamer.component.html'
})
export class GamerComponent implements OnInit, OnDestroy {
gamers: Gamer[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private jhiLanguageService: JhiLanguageService,
        private gamerService: GamerService,
        private alertService: AlertService,
        private eventManager: EventManager,
        private principal: Principal
    ) {
        this.jhiLanguageService.setLocations(['gamer']);
    }

    loadAll() {
        this.gamerService.query().subscribe(
            (res: Response) => {
                this.gamers = res.json();
            },
            (res: Response) => this.onError(res.json())
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInGamers();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId (index: number, item: Gamer) {
        return item.uuid;
    }



    registerChangeInGamers() {
        this.eventSubscriber = this.eventManager.subscribe('gamerListModification', (response) => this.loadAll());
    }


    private onError (error) {
        this.alertService.error(error.message, null, null);
    }
}
