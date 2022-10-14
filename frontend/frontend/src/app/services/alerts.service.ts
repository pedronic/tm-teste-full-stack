import { Injectable } from '@angular/core';
import { filter, Observable, Subject } from 'rxjs';
import { Alert, AlertType } from '../models/alert.model';

@Injectable({
  providedIn: 'root',
})
export class AlertsService {
  private subject = new Subject<Alert>();
  private defaultId = 'response-alert';


  constructor() {}

  public onAlert(id: string = this.defaultId) : Observable<Alert> {
    return this.subject
      .asObservable()
      .pipe(filter((x) => x && x.id === id));
  }

  public alert(alert: Alert) : void {
    alert.type = alert.type;
    this.subject.next(alert);
  }

  public clear(id: string = "", type: string = "", status: string = "", message: string = "") : void {
    this.subject.next(new Alert(id, type, status, message));
  }
}
