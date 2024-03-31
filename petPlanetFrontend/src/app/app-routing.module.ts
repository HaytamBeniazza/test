import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { AuthGuard } from './AuthGuard';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { AdminDashboardComponent } from './pages/admin-dashboard/admin-dashboard.component';
import { AboutPageComponent } from './pages/about-page/about-page.component';
import { ShopComponent } from './pages/shop/shop.component';
import { PetTableComponent } from './components/admin/pet-table/pet-table.component';
import { CategoryTableComponent } from './components/admin/category-table/category-table.component';
import { PetProductTableComponent } from './components/admin/pet-product-table/pet-product-table.component';
import { ProductTableComponent } from './components/admin/product-table/product-table.component';
import { ProductDetailsComponent } from './pages/product-details/product-details.component';
import { CartComponent } from './pages/cart/cart.component';

const routes: Routes = [
  { 
    path: '',
    component: HomePageComponent
  },
  { 
    path: 'login', 
    component: LoginComponent
  },
  {
    path: 'AdminDashboard', 
    component: AdminDashboardComponent,
    canActivate: [AuthGuard],
    data: { role: 'admin' },
  },
  {
    path: 'store', 
    component: ShopComponent,
    canActivate: [AuthGuard],
    data: { role: 'client' },
  },
  {
    path: 'about',
    component: AboutPageComponent
  },
  {
    path: 'cart',
    component: CartComponent,
    canActivate: [AuthGuard],
    data: { role: 'client' },
  },
  {
    path: 'product/:id',
    component: ProductDetailsComponent,
    canActivate: [AuthGuard],
    data: { role: 'client' },
  },
  {
    path: 'pettable',
    component: PetTableComponent,
    canActivate: [AuthGuard],
    data: { role: 'admin' },
  },
  {
    path: 'categorytable',
    component: CategoryTableComponent,
    canActivate: [AuthGuard],
    data: { role: 'admin' },
  },
  {
    path: 'petproducttable',
    component: PetProductTableComponent,
    canActivate: [AuthGuard],
    data: { role: 'admin' },
  },
  {
    path: 'producttable',
    component: ProductTableComponent,
    canActivate: [AuthGuard],
    data: { role: 'admin' },
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }