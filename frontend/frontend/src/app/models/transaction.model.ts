export class Transaction {
  public transactionId: string;
  public accountFrom: number;
  public accountTo: number;
  public value: number;
  public fee_value: number;
  public dateCreated: string;
  public dateScheduled: string;
  public executed: boolean;
  public fee: string;

  constructor(transactionId: string, accountFrom: number, accountTo: number, value: number, fee_value: number, dateCreated: string, dateScheduled: string, executed: boolean, fee: string) {
    this.transactionId = transactionId;
    this.accountFrom = accountFrom;
    this.accountTo = accountTo;
    this.value = value;
    this.fee_value = fee_value;
    this.dateCreated = dateCreated;
    this.dateScheduled = dateScheduled;
    this.executed = executed;
    this.fee = fee;
  }
}

export class NewTransactionPayload {
  public username: string;
  public accountFrom: number;
  public accountTo: number;
  public value: number;
  public dateScheduled: string;

  constructor(username: string, accountFrom: number, accountTo: number, value: number, dateScheduled: string){
    this.username = username;
    this.accountFrom = accountFrom;
    this.accountTo = accountTo;
    this.value = value;
    this.dateScheduled = dateScheduled;
  }
}

export class SimResponse {
  public valid: boolean;
  public data: Simulation;
  constructor(valid: boolean, data: Simulation){
    this.valid = valid;
    this.data = data;
  }
}

export class Simulation {
  public fee_rate: number;
  public fee_total: number;
  public fee_value: number;
  public subtotal: number;
  public type: string;

  constructor(fee_rate: number, fee_total: number, fee_value: number, subtotal: number, type: string){
    this.fee_rate = fee_rate;
    this.fee_total = fee_total;
    this.fee_value = fee_value;
    this.subtotal = subtotal;
    this.type = type;
  }
}
