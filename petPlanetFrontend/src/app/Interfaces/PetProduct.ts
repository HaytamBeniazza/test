import { Category } from "./Category";
import { Pet } from "./Pet";
import { Product } from "./Product";
import { Review } from "./Review";

export interface PetProduct {
    id: number;
    pet: Pet;
    product: Product;
    category: Category;
    review: Review[];
}