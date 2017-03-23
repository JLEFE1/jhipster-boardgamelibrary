import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService, JhiLanguageService } from 'ng-jhipster';

import { Gamer } from './gamer.model';
import { GamerPopupService } from './gamer-popup.service';
import { GamerService } from './gamer.service';
@Component({
    selector: 'jhi-gamer-dialog',
    templateUrl: './gamer-dialog.component.html'
})
export class GamerDialogComponent implements OnInit {

    gamer: Gamer;
    authorities: any[];
    isSaving: boolean;
    constructor(
        public activeModal: NgbActiveModal,
        private jhiLanguageService: JhiLanguageService,
        private alertService: AlertService,
        private gamerService: GamerService,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['gamer']);
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
    }
    clear () {
        this.activeModal.dismiss('cancel');
    }

    save () {
        this.isSaving = true;
        if (this.gamer.uuid !== undefined) {
            this.gamerService.update(this.gamer)
                .subscribe((res: Gamer) => this.onSaveSuccess(res), (res: Response) => this.onSaveError(res.json()));
        } else {
            this.gamerService.create(this.gamer)
                .subscribe((res: Gamer) => this.onSaveSuccess(res), (res: Response) => this.onSaveError(res.json()));
        }
    }

    private onSaveSuccess (result: Gamer) {
        this.eventManager.broadcast({ name: 'gamerListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError (error) {
        this.isSaving = false;
        this.onError(error);
    }

    private onError (error) {
        this.alertService.error(error.message, null, null);
    }
}

@Component({
    selector: 'jhi-gamer-popup',
    template: ''
})
export class GamerPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor (
        private route: ActivatedRoute,
        private gamerPopupService: GamerPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe(params => {
            if ( params['uuid'] ) {
                this.modalRef = this.gamerPopupService
                    .open(GamerDialogComponent, params['uuid']);
            } else {
                this.modalRef = this.gamerPopupService
                    .open(GamerDialogComponent);
            }

        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
