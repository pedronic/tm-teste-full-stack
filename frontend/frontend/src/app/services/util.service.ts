import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import jwt_decode from 'jwt-decode';
import { Observable, of } from 'rxjs';
import { Token } from '../models/token.model';

@Injectable({
  providedIn: 'root',
})
export class UtilService {
  private readonly LOGIN_TOKEN: string = 'LOGIN_TOKEN';

  constructor() {}

  public objectToJSON(obj: Object): string {
    return JSON.stringify(obj);
  }

  public JSONtoObject(json: string): any {
    return JSON.parse(json);
  }

  public setLocalStorageItem(
    item: Object | string | null,
    itemName: string = this.LOGIN_TOKEN
  ): void {
    if (item === null) {
      return;
    }
    if (typeof item == 'object' && item != null) {
      localStorage.setItem(itemName, this.objectToJSON(item));
      return;
    }
    if (typeof item == 'string' && item != null) {
      localStorage.setItem(itemName, item);
      return;
    }
  }

  public getToken(): any {
    if (this.getLocalStorageItem()) {
      return this.JSONtoObject(this.getLocalStorageItem())?.Authorization;
    }
  }

  public getLocalStorageItem(itemName: string = this.LOGIN_TOKEN): any {
    return localStorage.getItem(itemName);
  }

  public deleteLocalStorageItem(itemName: string = this.LOGIN_TOKEN): void {
    return localStorage.removeItem(itemName);
  }

  public getUserInfo(): any {
    if (this.getLocalStorageItem()) {
      let res = jwt_decode<Token>(<string>this.getLocalStorageItem());
      return res.sub;
    }
    return 'No info to be displayed';
  }

  public isLoggedIn(): boolean {
    if (this.getLocalStorageItem(this.LOGIN_TOKEN)) {
      return true;
    }
    return false;
  }

  public reloadPage(router: Router): void {
    const currentUrl = router.url;
    router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      router.navigate([currentUrl]);
    });
  }
}
