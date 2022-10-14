import { NewTransactionPayload } from './../models/transaction.model';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable, of } from 'rxjs';
import { Account } from '../models/account.model';
import { Transaction, SimResponse } from '../models/transaction.model';
import { ApiService } from '../services/api.service';
import { UtilService } from '../services/util.service';

const defaultSimResponse = {
  valid: null,
  data: {
    fee_rate: null,
    fee_total: null,
    fee_value: null,
    subtotal: null,
    type: null,
  },
};
@Component({
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  public TODAY: Date = new Date();

  // Data sources
  public userAccounts: Array<Account>;
  public allAccounts: Array<Account>;
  public filteredAccounts: Array<Account>;
  public transactions: Array<Transaction> = [];
  public simulation: SimResponse;

  // Forms
  public form: FormGroup;
  public formSim: FormGroup;

  // Control variables
  public scheduled: boolean = false;

  // Data Transfer variable
  private newTransaction: NewTransactionPayload;

  constructor(
    private _formBuilder: FormBuilder,
    private _apiService: ApiService,
    private _utilService: UtilService
  ) {
    this.simulation = new SimResponse(
      defaultSimResponse.valid,
      defaultSimResponse.data
    );
    this.form = _formBuilder.group({
      accountFrom: [null, [Validators.required]],
      accountTo: [null, [Validators.required]],
      value: [null, [Validators.required, Validators.min(0.01)]],
      dateScheduled: [null, [Validators.required]],
    });
    this.formSim = _formBuilder.group({
      fee_rate: [
        { value: this.simulation.data.fee_rate, disabled: true },
        [Validators.required],
      ],
      fee_total: [
        { value: this.simulation.data.fee_total, disabled: true },
        [Validators.required],
      ],
      fee_value: [
        { value: this.simulation.data.fee_value, disabled: true },
        [Validators.required],
      ],
      subtotal: [
        { value: this.simulation.data.subtotal, disabled: true },
        [Validators.required],
      ],
      type: [
        { value: this.simulation.data.type, disabled: true },
        [Validators.required],
      ],
    });
  }

  ngOnInit(): void {
    this._apiService.getUserAccounts().subscribe((accounts: Account[]) => {
      this.userAccounts = accounts;
    });
    this._apiService.getAllAccounts().subscribe((accounts: Account[]) => {
      this.filteredAccounts = this.allAccounts = accounts;
    });
    this._apiService
      .getUserTransactions()
      .subscribe((transactions: Transaction[]) => {
        this.transactions = transactions;
      });
  }

  public canSchedule(): boolean {
    if (!this.scheduled) {
      return false;
    }
    if (!(this.simulation.data.subtotal > 0)) {
      return false;
    }
    return true;
  }

  public filterAccounts(): void {
    this.filteredAccounts = this.allAccounts.filter((account) => {
      return account.accountNumber !== this.form.get('accountFrom').value;
    });
    this.scheduled = false;
  }

  public hasAccountFrom(): boolean {
    if (this.form.get('accountFrom')?.pristine) {
      return false;
    }
    if (!(this.form.get('accountFrom')?.value > 0)) {
      return false;
    }
    return true;
  }

  public hasAccountTo(): boolean {
    if (this.form.get('accountTo')?.pristine) {
      return false;
    }
    if (!(this.form.get('accountTo')?.value > 0)) {
      return false;
    }
    return true;
  }

  public hasErrors(): boolean {
    if (this.form.pristine) {
      return true;
    }
    if (
      !(this.form.get('accountFrom')?.value > 0) ||
      this.form.get('accountFrom')?.pristine
    ) {
      return true;
    }
    if (
      !(this.form.get('accountTo')?.value > 0) ||
      this.form.get('accountTo')?.pristine
    ) {
      return true;
    }
    if (
      !(this.form.get('value')?.value > 0) ||
      this.form.get('value')?.pristine
    ) {
      return true;
    }
    return false;
  }

  public maskValue(): void {
    if (this.form.get('value').value === null) {
      return;
    }
    this.form.get('value').setValue(this.form.get('value').value.toFixed(2));
  }

  public onEdited(): void {
    this.scheduled = false;
  }

  public schedule(): void {
    this._apiService.newTransaction(this.newTransaction).subscribe({
      next: () => {},
      error: () => {},
      complete: () => {
        this.form.reset();
        this.formSim.reset();
        this._apiService
          .getUserTransactions()
          .subscribe((transactions: Transaction[]) => {
            this.transactions = transactions;
          });
          this.scheduled = false;
      },
    });
  }

  public simulate(): void {
    let _new_trans = new NewTransactionPayload(
      this._utilService.getUserInfo(),
      this.form.get('accountFrom').value,
      this.form.get('accountTo').value,
      this.form.get('value').value,
      this.form.get('dateScheduled').value
    );
    this.newTransaction = _new_trans;
    this._apiService.simTransaction(_new_trans).subscribe({
      next: (res) => {
        this.simulation = res;
        this.formSim
          .get('fee_rate')
          .setValue((this.simulation.data.fee_rate * 100).toFixed(2));
        this.formSim
          .get('fee_total')
          .setValue((this.simulation.data.fee_total * 1).toFixed(2));
        this.formSim
          .get('fee_value')
          .setValue((this.simulation.data.fee_value * 1).toFixed(2));
        this.formSim
          .get('subtotal')
          .setValue((this.simulation.data.subtotal * 1).toFixed(2));
        this.formSim
          .get('subtotal')
          .setErrors({ negative_balance: this.simulation.valid });
        this.formSim.get('type').setValue(this.simulation.data.type);
      },
      error: () => {},
      complete: () => {
        this.scheduled = true;
      },
    });
  }
}
