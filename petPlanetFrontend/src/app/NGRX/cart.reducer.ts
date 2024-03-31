import { createReducer, on } from '@ngrx/store';
import { OrderRequest } from '../Interfaces/OrderRequest';
import * as CartActions from './cart.actions';

export const initialState: OrderRequest[] = [];

export const cartReducer = createReducer(
  initialState,
  on(CartActions.addToCart, (state, { order }) => {
    // Check if the order already exists in the cart
    const existingOrder = state.find(existingOrder => existingOrder.OrderId === order.OrderId);
    
    if (existingOrder) {
      // If the order exists, create a new state array with updated order lines
      return state.map(o => {
        if (o.OrderId === order.OrderId) {
          return { ...o, orderLines: [...o.orderLines, ...order.orderLines] };
        }
        return o;
      });
    } else {
      // If the order doesn't exist, add it to the cart
      return [...state, order];
    }
  }),
  on(CartActions.removeOrderLine, (state, { orderId, orderLineId }) => {
    // Find the order in the state
    const orderToUpdate = state.find(order => order.OrderId === orderId);
    
    // If the order is found
    if (orderToUpdate) {
      // Filter out the order line to be removed
      const updatedOrderLines = orderToUpdate.orderLines.filter(orderLine => orderLine.id !== orderLineId);
      
      // Create a new order with updated order lines
      const updatedOrder = { ...orderToUpdate, orderLines: updatedOrderLines };
      
      // Create a new state array with the updated order
      state = state.map(order => (order.OrderId === orderId ? updatedOrder : order));
    }
    
    // Return the original state if the order is not found
    return state;
  }),
  on(CartActions.emptyCart, () => {
    return [];
  })
);

