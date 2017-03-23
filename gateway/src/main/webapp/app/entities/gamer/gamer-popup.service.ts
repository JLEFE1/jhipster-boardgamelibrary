import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { GamerService } from './gamer.service';
import {Gamer} from './gamer.model';
@Injectable()
export class GamerPopupService {
    private isOpen = false;
    constructor (
        private modalService: NgbModal,
        private router: Router,
        private gamerService: GamerService

    ) {}

    open (component: Component, uuid?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (uuid) {
            this.gamerService.find(uuid).subscribe(gamer => {
                this.gamerModalRef(component, gamer);
            });
        } else {
            return this.gamerModalRef(component, new Gamer());
        }
    }

    gamerModalRef(component: Component, gamer: Gamer): NgbModalRef {
        let modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.gamer = gamer;
        modalRef.result.then(result => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.isOpen = false;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.isOpen = false;
        });
        return modalRef;
    }
}
