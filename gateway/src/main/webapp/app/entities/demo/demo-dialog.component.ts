import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService, JhiLanguageService } from 'ng-jhipster';

import { Demo } from './demo.model';
import { DemoPopupService } from './demo-popup.service';
import { DemoService } from './demo.service';
@Component({
    selector: 'jhi-demo-dialog',
    templateUrl: './demo-dialog.component.html'
})
export class DemoDialogComponent implements OnInit {

    demo: Demo;
    authorities: any[];
    isSaving: boolean;
    constructor(
        public activeModal: NgbActiveModal,
        private jhiLanguageService: JhiLanguageService,
        private alertService: AlertService,
        private demoService: DemoService,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['demo']);
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
        if (this.demo.id !== undefined) {
            this.demoService.update(this.demo)
                .subscribe((res: Demo) => this.onSaveSuccess(res), (res: Response) => this.onSaveError(res.json()));
        } else {
            this.demoService.create(this.demo)
                .subscribe((res: Demo) => this.onSaveSuccess(res), (res: Response) => this.onSaveError(res.json()));
        }
    }

    private onSaveSuccess (result: Demo) {
        this.eventManager.broadcast({ name: 'demoListModification', content: 'OK'});
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
    selector: 'jhi-demo-popup',
    template: ''
})
export class DemoPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor (
        private route: ActivatedRoute,
        private demoPopupService: DemoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe(params => {
            if ( params['id'] ) {
                this.modalRef = this.demoPopupService
                    .open(DemoDialogComponent, params['id']);
            } else {
                this.modalRef = this.demoPopupService
                    .open(DemoDialogComponent);
            }

        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
