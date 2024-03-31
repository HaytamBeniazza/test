import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';


import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ReactiveFormsModule } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import { TableModule } from 'primeng/table';
import { TagModule } from 'primeng/tag';
import { DropdownModule } from 'primeng/dropdown';
import { MultiSelectModule } from 'primeng/multiselect';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { StoreModule } from '@ngrx/store';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { AboutPageComponent } from './pages/about-page/about-page.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';
import { AdminDashboardComponent } from './pages/admin-dashboard/admin-dashboard.component';
import { LoginComponent } from './pages/login/login.component';
import { CustomInterceptor } from './interceptor/costum.interceptor';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { StoreComponent } from './pages/store/store.component';
import { CartComponent } from './pages/cart/cart.component';
import { ShopComponent } from './pages/shop/shop.component';
import { PetProductCardComponent } from './components/pet-product-card/pet-product-card.component';
import { PetCardComponent } from './components/pet-card/pet-card.component';
import { AddPrductComponent } from './components/admin/add-prduct/add-prduct.component';
import { ProductTableComponent } from './components/admin/product-table/product-table.component';
import { AddPetComponent } from './components/admin/add-pet/add-pet.component';
import { PetTableComponent } from './components/admin/pet-table/pet-table.component';
import { AddCategoryComponent } from './components/admin/add-category/add-category.component';
import { CategoryTableComponent } from './components/admin/category-table/category-table.component';
import { FormComponentComponent } from './components/admin/form-component/form-component.component';
import { authReducer } from './NGRX/auth.reducer';
import { PetProductTableComponent } from './components/admin/pet-product-table/pet-product-table.component';
import { PopupComponent } from './components/popup/popup.component';
import { ProductDetailsComponent } from './pages/product-details/product-details.component';
import { cartReducer } from './NGRX/cart.reducer';

@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    AboutPageComponent,
    NavbarComponent,
    FooterComponent,
    AdminDashboardComponent,
    LoginComponent,
    StoreComponent,
    CartComponent,
    ShopComponent,
    PetProductCardComponent,
    PetCardComponent,
    AddPrductComponent,
    ProductTableComponent,
    AddPetComponent,
    PetTableComponent,
    AddCategoryComponent,
    CategoryTableComponent,
    FormComponentComponent,
    PetProductTableComponent,
    PopupComponent,
    ProductDetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    StoreModule.forRoot({auth: authReducer}),
    StoreModule.forFeature('cart', cartReducer),
    BrowserAnimationsModule,
    TableModule,
    TagModule,
    DropdownModule,
    MultiSelectModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: CustomInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
