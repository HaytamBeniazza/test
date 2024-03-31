import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Store, select } from '@ngrx/store';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { OrderLine } from 'src/app/Interfaces/OrderLine';
import { OrderRequest } from 'src/app/Interfaces/OrderRequest';
import { PetProduct } from 'src/app/Interfaces/PetProduct';
import { addToCart } from 'src/app/NGRX/cart.actions';
import { selectLoggedInUser } from 'src/app/NGRX/auth.selectors';
import { PetProductService } from 'src/app/services/petProduct/pet-product.service';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.scss'],
})
export class ProductDetailsComponent implements OnInit {
  id: number = 0;
  petProduct!: PetProduct;
  orderForm!: FormGroup;
  // userId$: Observable<number>;
  userId: number = 0;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private store: Store,
    private petProductService: PetProductService,
    private sanitizer: DomSanitizer
  ) {
    // this.userId$ = this.store.pipe(
    //   select(selectLoggedInUser),
    //   map(user => user?.id ?? 0)
    // );
  }

  getImageUrl(companyImage:any) {
    const imageUrl = `data:${companyImage.type};base64,${companyImage.data}`;
    return this.sanitizer.bypassSecurityTrustUrl(imageUrl);
  }
  decrementQuantity(): void {
    let currentQuantity = this.orderForm.get('quantity')?.value;
    currentQuantity = currentQuantity > 1 ? currentQuantity - 1 : 1;
    this.orderForm.patchValue({ quantity: currentQuantity });
  }

  incrementQuantity(): void {
    let currentQuantity = this.orderForm.get('quantity')?.value;
    currentQuantity += 1;
    this.orderForm.patchValue({ quantity: currentQuantity });
  }

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.id = params['id'];
    });
    this.getpetProduct();
    this.initializeProductForm();
    const loggedInn = localStorage.getItem('user');
    if (loggedInn) {
      this.userId = JSON.parse(loggedInn).id;
    }
  }

  getpetProduct() {
    this.petProductService.getProductById(this.id).subscribe((product) => {
      this.petProduct = product;
    });
  }

  initializeProductForm(): void {
    this.orderForm = this.formBuilder.group({
      quantity: [1],
    });
  }

  addToCart(): void {
    const quantity = this.orderForm.get('quantity')?.value;
    if (quantity > 0 && this.petProduct) {
      const orderLine: OrderLine = {
        id: this.petProduct.id,
        quantity: quantity,
        dateTime: new Date(),
        petProduct: this.petProduct,
      };

      // let userId: number = 0;
      // this.userId$.subscribe(id => userId = id);

      const order: OrderRequest = {
        OrderId: 1,
        total: 0,
        status: 'Pending',
        clientId: this.userId,
        orderLines: [orderLine],
      };
      this.store.dispatch(addToCart({ order }));
      this.router.navigate(['/cart']);
    } else {
      console.error('Invalid quantity or product details');
    }
  }
}
