import { Component, OnInit } from '@angular/core';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, JhiLanguageService, AlertService } from 'ng-jhipster';

import { Account, LoginModalService, Principal } from '../../shared';
import { GamerDatabaseService } from '../../shared/database/gamer-database.service';
import {Gamer} from '../../entities/gamer/gamer.model';

@Component({
    selector: 'jhi-gamer',
    templateUrl: './gamer.component.html'
})
export class GamerComponent implements OnInit {
    modalRef: NgbModalRef;
    gamers: Gamer[] = [];

    addGamer: any = (gamer) => {
        this.gamers.push(gamer);
    }

    constructor(private jhiLanguageService: JhiLanguageService,
                private principal: Principal,
                private gamerDatabaseService: GamerDatabaseService,
                private alertService: AlertService) {
        this.jhiLanguageService.setLocations(['gamer']);
    }

    ngOnInit() {
        this.principal.identity().then((gamers) => {
            this.gamerDatabaseService.getGamers();
        });
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}
