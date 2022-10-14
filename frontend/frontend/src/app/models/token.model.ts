export class Token {
  public sub: string;
  public authorities: Array<any>;
  public iat: number;
  public exp: number;

  constructor(sub: string, authorities: Array<any>, iat: number, exp: number) {
    this.sub = sub;
    this.authorities = authorities;
    this.iat = iat;
    this.exp = exp;
  }
}
