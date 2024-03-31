import { Injectable } from '@angular/core';
import {
  CanActivate,
  Router,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
} from '@angular/router';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { AuthState } from './NGRX/auth.reducer';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(
    private store: Store<{ auth: AuthState }>,
    private router: Router
  ) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean> {
    return this.store.select('auth').pipe(
      take(1),
      map((authState: AuthState) => {
        if (authState.isAuthenticated || localStorage.getItem('role')) {
          const requiredRole = route.data['role'] as string;
          if (localStorage.getItem('role') === requiredRole ){
            return true;
          } else if (localStorage.getItem('role') === 'user' && requiredRole === 'user') {
            this.router.navigate(['/unauthorized']);
            return false;
          }
        } else {
          this.router.navigate(['/login']);
          return false;
        }
        return false; // Add a default value of false
      })
    );
  }
}
