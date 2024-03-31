import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Store, createFeatureSelector } from '@ngrx/store';
import { AuthState } from 'src/app/NGRX/auth.reducer';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss'],
})
export class NavbarComponent implements OnInit {
  // isLoggedIn = false;
  loggedIn = localStorage.getItem('user');
  // if (this.loggedIn) {
  //   const userExist: boolean = JSON.parse(this.loggedIn);
  //   console.log("userExist.id");
  // }

  constructor(
    private store: Store,
    private router: Router,
    private cdr: ChangeDetectorRef,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    const loggedInn = localStorage.getItem('user');
    if (loggedInn) {
      const userExist = JSON.parse(loggedInn);
      console.log('id: ' + userExist.id);
    }

    // this.authState$.subscribe(() => {
    //   this.cdr.detectChanges();
    // });
    var sss = JSON.parse(localStorage.getItem('user') || '');
  }

  login() {
    this.router.navigate(['/login']);
  }

  logout() {
    this.authService.logout();
  }
}
