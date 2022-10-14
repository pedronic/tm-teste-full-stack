import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Alert, AlertType, TYPES } from '../../models/alert.model';
import { AlertsService } from 'src/app/services/alerts.service';

@Component({
  selector: 'app-alert',
  templateUrl: './alert.component.html',
  styleUrls: ['./alert.component.scss'],
})
export class AlertComponent implements OnInit, OnDestroy {

  @Input() id = "response-alert";

  public _TYPES = TYPES;

  private type: AlertType = AlertType.Default;

  public alerts: Alert[] = [];

  private alertSubs: Subscription = new Subscription();
  constructor(
    private _alertService: AlertsService,
  ) {
  }

  ngOnInit(): void {
    this.alertSubs = this._alertService
      .onAlert()
      .subscribe((alert: Alert) => {
        if (!alert.id) {
          this.alerts = [];
          return;
        }
        this.alerts.push(alert);
        setTimeout(() => this.removeAlert(alert), 3000);
      });
  }

  public removeAlert(alert: Alert) {
    if (!this.alerts.includes(alert)) {
      return;
    }

    setTimeout(() => {
      this.alerts = this.alerts.filter((x) => x !== alert);
    }, 250);
  }

  ngOnDestroy(): void {
    this.alertSubs.unsubscribe();
  }
}
