import { Component, OnInit } from '@angular/core';
import { Store, select } from '@ngrx/store';
import { Observable } from 'rxjs';
import { emptyCart, removeOrderLine } from 'src/app/NGRX/cart.actions';
import { OrderRequest } from 'src/app/Interfaces/OrderRequest';
import { selectCartItems , selectCartTotalPrice } from 'src/app/NGRX/cart.selectors';
import { CartService } from 'src/app/services/cart/cart.service';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {
  cartItems$: Observable<OrderRequest[]>;
  orderRequest! : OrderRequest ;
  totalSelector$: Observable<number>;
  total: number = 0;

  constructor(private store: Store,private cartService: CartService,private sanitizer: DomSanitizer) {
    this.cartItems$ = this.store.pipe(select(selectCartItems));
    this.totalSelector$ = this.store.pipe(select(selectCartTotalPrice));
  }

  ngOnInit(): void {
    this.orderRequest = {} as OrderRequest;
    this.total = 0;
  
    this.cartItems$.subscribe(items => {
      this.orderRequest = { ...items[0] } || {} as OrderRequest; 
    });
  
    this.totalSelector$.subscribe(total => {
      this.total = total;
      this.orderRequest = { ...this.orderRequest, total: total };
    });
  }
  

  getImageUrl(companyImage:any) {
    const imageUrl = `data:${companyImage.type};base64,${companyImage.data}`;
    return this.sanitizer.bypassSecurityTrustUrl(imageUrl);
  }
  removeOrderLine(orderId: number, orderLineId: number): void {
    this.store.dispatch(removeOrderLine({ orderId, orderLineId }));
  }

  placeOrder(): void {
    this.cartService.order(this.orderRequest).subscribe(
      (order) => {
        console.log('Order placed successfully:', order);
        this.store.dispatch(emptyCart());

      },
      (error) => {
        this.store.dispatch(emptyCart());
      }
    );
  }
}
