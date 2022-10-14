import { Component } from '@angular/core';
import { AuthService } from './services/auth.service';
import { UtilService } from './services/util.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent {
  title = 'frontend';

  constructor(public utilService: UtilService, public authService: AuthService) {}
}
