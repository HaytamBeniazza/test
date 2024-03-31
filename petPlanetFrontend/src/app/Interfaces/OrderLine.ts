import { Order } from "./OrderItem";
import { PetProduct } from "./PetProduct";
import { Product } from "./Product";

export interface OrderLine {
    id: number; 
    quantity: number;
    dateTime: Date;
    petProduct: PetProduct;
  }