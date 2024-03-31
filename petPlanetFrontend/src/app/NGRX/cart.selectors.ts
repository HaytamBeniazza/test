import { createFeatureSelector, createSelector } from '@ngrx/store';
import { OrderRequest } from '../Interfaces/OrderRequest';

export const selectCartState = createFeatureSelector<OrderRequest[]>('cart');

export const selectCartItems = createSelector(
  selectCartState,
  (cart: OrderRequest[]) => cart
);

export const selectCartItemCount = createSelector(
  selectCartItems,
  (cart: OrderRequest[]) => cart.length
);

export const selectCartTotalPrice = createSelector(
  selectCartItems,
  (cart: OrderRequest[]) => {
    let totalPrice = 0;

    cart.forEach(orderRequest => {
      orderRequest.orderLines.forEach(orderLine => {
        totalPrice += (orderLine.petProduct?.product?.price ?? 0) * orderLine.quantity;
      });
    });

    return totalPrice;
  }
);
