import { createAction, props } from '@ngrx/store';
import { OrderRequest } from '../Interfaces/OrderRequest';
import { OrderLine } from '../Interfaces/OrderLine';

export const addToCart = createAction('[Cart] Add To Cart', props<{ order: OrderRequest }>());

export const removeOrderLine = createAction(
    '[Cart] Remove Order Line',
    props<{ orderId: number; orderLineId: number }>()
);

export const emptyCart = createAction('[Cart] Empty Cart');