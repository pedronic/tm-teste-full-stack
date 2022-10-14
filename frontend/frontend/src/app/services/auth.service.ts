import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, Observer } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Alert, AlertType, ID_DEFAULT } from '../models/alert.model';
import { User } from '../models/user.model';
import { AlertsService } from './alerts.service';
import { UtilService } from './util.service';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly LOGIN_URL: string = `${environment.BASE_URL}login`;

  private loginObserver: Observer<any> = {
    next: (response: HttpResponse<any>) => {
      this._utilService.setLocalStorageItem(response);
      this._alertService.alert(
        new Alert(
          ID_DEFAULT,
          AlertType.Success,
          'IT WORKED!',
          'You are now logged in. Enjoy the recently enabled features'
        )
      );
      setTimeout(() => this._router.navigate(['/']), 3000);
    },
    error: (error) => {
      this._alertService.alert(
        new Alert(
          ID_DEFAULT,
          AlertType.Danger,
          `${error.error.statusCode}: ${error.error.type}`,
          `${error.error.message}`
        )
      );
      setTimeout(() => this._utilService.reloadPage(this._router), 3000);
    },
    complete: () => {},
  };

  constructor(
    private _utilService: UtilService,
    private _alertService: AlertsService,
    private _router: Router,
    private http: HttpClient
  ) {}

  public logout(): void {
    this._alertService.alert(
      new Alert(
        ID_DEFAULT,
        AlertType.Info,
        'Signing you out...',
        "You'll be redirected automatically."
      )
    );
    this._utilService.deleteLocalStorageItem();
    setTimeout(() => this._router.navigate(['/login']), 3000);
  }

  public login(payload: User): void {
    this._alertService.alert(
      new Alert(
        ID_DEFAULT,
        AlertType.Info,
        'Attempting to login to server...',
        "You'll be redirected automatically or this page will reload."
      )
    );
    this.http
      .post(this.LOGIN_URL, payload, {
        headers: { 'Content-Type': 'application/json' },
      })
      .subscribe(this.loginObserver);
  }
}
