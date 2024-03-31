import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PetProductService } from 'src/app/services/petProduct/pet-product.service';
import { Review } from 'src/app/Interfaces/Review';
import { Category } from 'src/app/Interfaces/Category';
import { Product } from 'src/app/Interfaces/Product';
import { Pet } from 'src/app/Interfaces/Pet';
import { ReviewService } from 'src/app/services/review/review.service';
import { CategoryService } from 'src/app/services/category/category.service';
import { ProductService } from 'src/app/services/product/product.service';
import { PetService } from 'src/app/services/pet/pet.service';
import { PopupComponent } from '../../popup/popup.component';

@Component({
  selector: 'app-pet-product-table',
  templateUrl: './pet-product-table.component.html',
  styleUrls: ['./pet-product-table.component.scss']
})
export class PetProductTableComponent implements OnInit {
  petProducts: any[] = [];
  petProductForm!: FormGroup;
  reviews: Review[] = [];
  categories: Category[] = [];
  products: Product[] = [];
  pets: Pet[] = [];
  @ViewChild(PopupComponent) popupComponent!: PopupComponent;

  constructor(
    private petProductService: PetProductService,
    private reviewsService : ReviewService,
    private categoryService : CategoryService,
    private productService : ProductService,
    private petService : PetService,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.getpetProducts();
    this.getReviews();
    this.getCategories();
    this.getProducts();
    this.getPets();
    this.initializePetProductForm();
  }

  getpetProducts(): void {
    this.petProductService.getAllProducts(0, 10)
      .subscribe(petProducts => {
        this.petProducts = petProducts;
      });
  }

  getReviews(): void {
    this.reviewsService.getAllReviews(0, 10).subscribe(reviews => {
      this.reviews = reviews;
    },
    (error)=>{
      console.log(error);
    }); 
  }

  getCategories(): void {
    this.categoryService.getAllCategories(0, 10).subscribe(categories => {
      this.categories = categories;
    },
    (error)=>{
      console.log(error);
    });
  }

  getProducts(): void {
    this.productService.getAllProducts(0, 10).subscribe(products => {
      this.products = products;
    },
    (error)=>{
      console.log(error);
    });  
  }

  getPets(): void {
    this.petService.getAllPets(0, 10).subscribe(pets => {
      this.pets = pets;
    },
    (error)=>{
      console.log(error);
    });
  }

  initializePetProductForm(): void {
    this.petProductForm = this.formBuilder.group({
      productId: ['', Validators.required],
      petId: ['', Validators.required],
      categoryId: ['', Validators.required],
    });
  }

  addPetProduct(): void {
    if (this.petProductForm.valid) {
      const newPetProduct: any = this.petProductForm.value;
      this.petProductService.addProduct(newPetProduct)
        .subscribe((res) => {
          this.getpetProducts();
          this.petProductForm.reset();
          this.popupComponent.Toggle();
        });
    }
  }

  deletePetProduct(petProductId: number): void {
    this.petProductService.deleteProduct(petProductId)
      .subscribe(() => {
        this.petProducts = this.petProducts.filter(petProduct => petProduct.id !== petProductId);
      });
  }
}
