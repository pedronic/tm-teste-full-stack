import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, pluck } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Account } from '../models/account.model';
import { NewTransactionPayload, SimResponse, Transaction } from '../models/transaction.model';
import { UtilService } from './util.service';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  private readonly API_URL: string = `${environment.BASE_URL}api/v1/`;
  private readonly ACCOUNT_EP: string = 'accounts';
  private readonly TRANSACTION_EP: string = 'transactions';

  constructor(private _utilService: UtilService, private http: HttpClient) {}

  public getAllAccounts(): Observable<Account[] | any> {
    return this.http
      .get<Account[]>(`${this.API_URL}${this.ACCOUNT_EP}`)
      .pipe(pluck('content'));
  }

  public getUserAccounts(): Observable<Account[]> {
    return this.http.get<Account[]>(
      `${this.API_URL}${this.ACCOUNT_EP}/${this._utilService.getUserInfo()}`
    );
  }

  public getUserTransactions(): Observable<Transaction[]> {
    return this.http.get<Transaction[]>(
      `${this.API_URL}${this.TRANSACTION_EP}/${this._utilService.getUserInfo()}`
    );
  }

  public simTransaction(payload: NewTransactionPayload): Observable<SimResponse> {
    return this.http.post<SimResponse>(`${this.API_URL}${this.TRANSACTION_EP}/sim`,payload);
  }

  public newTransaction(payload: NewTransactionPayload): Observable<SimResponse> {
    return this.http.post<SimResponse>(`${this.API_URL}${this.TRANSACTION_EP}/new`,payload);
  }
}
