import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs';
import {Gamer} from '../../entities/gamer/gamer.model';

@Injectable()
export class GamerDatabaseService {
    constructor(private _http: Http) {
    }

    public getGamers(): Observable<Gamer[]> {
        return this._http.get('http://localhost:8080/users/rest/gamer').map((response: Response) => {
            let body = response.json();
            let gamers = [];
            for (let i = 0; i < body.length; i++) {
                debugger;
                let gameResponse = body[i];
                let gamer: Gamer = {
                    username: gameResponse.username,
                    email: gameResponse.email,
                };
                gamers.push(gamer);
            }
            return gamers;
        });
    }

}
