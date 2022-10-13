import { Component, OnInit } from '@angular/core';

@Component({
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  public today: Date = new Date();

  public dataSource: any[] = [];
  /* [
    {
      accountFrom : 30,
      accountTo : 739,
      value : 7654.98,
      fee : 343.00,
      dateScheduled : '2022-12-10T09:33:43+0000' ,
      executed : false,
      dateCreated : '2022-10-13T09:33:43+0000'
    }
  ]; */
  public subtotal: string = '100.00';

  constructor() {}

  ngOnInit(): void {}

  public simulate(): void {}

  public schedule(): void {}
}
