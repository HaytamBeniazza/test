import { Client } from "./Client";
import { OrderLine } from "./OrderLine";

export interface Order {
    id: number; 
    total: number; 
    status: string; 
    client: Client;
    orderLines: OrderLine[];
}