export class Alert {
  public id: string;
  public type: string;
  public status: string;
  public message: string;

  constructor(id: string = ID_DEFAULT, type: string, status: string, message: string){
    this.id = id;
    this.type = type;
    this.status = status;
    this.message = message;
  }
}

export enum AlertType {
  Success = "success",
  Info = "info",
  Warning = "warning",
  Danger = "danger",
  Default = ""
}

export const TYPES: any = {
  [AlertType.Success]: 'success',
  [AlertType.Info]: 'info',
  [AlertType.Warning]: 'warning',
  [AlertType.Danger]: 'danger',
  [AlertType.Default]: '',
};

export const ID_DEFAULT: string = "response-alert";
