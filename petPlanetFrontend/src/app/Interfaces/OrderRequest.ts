import { OrderLine } from "./OrderLine";

export interface OrderRequest {
  OrderId: number;
  total: number; 
  status: string; 
  clientId: number;
  orderLines: OrderLine[];
}