import { Component, OnInit } from '@angular/core';
import { Category } from 'src/app/Interfaces/Category';
import { Pet } from 'src/app/Interfaces/Pet';
import { PetProduct } from 'src/app/Interfaces/PetProduct';
import { CategoryService } from 'src/app/services/category/category.service';
import { PetService } from 'src/app/services/pet/pet.service';
import { PetProductService } from 'src/app/services/petProduct/pet-product.service';

@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.scss']
})
export class ShopComponent implements OnInit {
  petProducts: PetProduct[] = [];
  filteredPetProducts: PetProduct[] = [];
  categories: Category[] = [];
  pets: Pet[] = [];
  currentPage = 1;
  pageSize = 6;
  totalPages: number = 0;
  pagesArray: number[] = [];
  selectedCategories: number[] = [];
  selectedPets: number[] = [];

  constructor(
    private petProductService: PetProductService,
    private petService: PetService,
    private categoryService: CategoryService
  ) {}

  ngOnInit(): void {
    this.petProductService.getTotalPagesNumber(this.pageSize)
      .subscribe(totalPages => {
        this.totalPages = totalPages;
        this.pagesArray = Array.from({ length: this.totalPages }, (_, index) => index + 1);
      });

    this.onPageChange(this.currentPage);
  }

  selectCategory(id: number): void {
    const index = this.selectedCategories.indexOf(id);
    if (index === -1) {
      this.selectedCategories.push(id);
    } else {
      this.selectedCategories.splice(index, 1);
    }
    this.applyFilters();
  }

  selectPet(id: number): void {
    const index = this.selectedPets.indexOf(id);
    if (index === -1) {
      this.selectedPets.push(id);
    } else {
      this.selectedPets.splice(index, 1);
    }
    this.applyFilters();
  }

  onPageChange(page: number): void {
    this.currentPage = page;
    this.loadPets();
    this.loadCategories();
    this.loadPetProducts();
  }

  loadPets(): void {
    this.petService.getAllPets(this.currentPage - 1, this.pageSize)
      .subscribe(pets => {
        this.pets = pets;
        console.log('Loaded Pets:', this.pets);
        this.applyFilters();
      });
  }

  loadCategories(): void {
    this.categoryService.getAllCategories(this.currentPage - 1, this.pageSize)
      .subscribe(categories => {
        this.categories = categories;
        console.log('Loaded Categories:', this.categories);
        this.applyFilters();
      });
  }

  loadPetProducts(): void {
    this.petProductService.getAllProducts(this.currentPage - 1, this.pageSize)
      .subscribe(petProducts => {
        this.petProducts = petProducts;
        console.log('Loaded Pet Products:', this.petProducts);
        this.applyFilters();
      });
  }

  applyFilters(): void {
    this.filteredPetProducts = this.petProducts.filter(petProduct => {
      const petMatch = this.selectedPets.length === 0 || this.selectedPets.includes(petProduct.pet.id);
      const categoryMatch = this.selectedCategories.length === 0 || this.selectedCategories.includes(petProduct.category.id);
      console.log(`Filtering: Pet ID ${petProduct.pet.id}, Category ID ${petProduct.category.id}, Pet Match: ${petMatch}, Category Match: ${categoryMatch}`);
      return petMatch && categoryMatch;
    });
    console.log('Filtered Pet Products:', this.filteredPetProducts);
  }

  filterByPriceHighToLow(): void {
    this.filteredPetProducts = this.petProducts.slice().sort((a, b) => b.product.price - a.product.price);
  }

  filterByPriceLowToHigh(): void {
    this.filteredPetProducts = this.petProducts.slice().sort((a, b) => a.product.price - b.product.price);
  }

  onPriceFilterChange(event: Event): void {
    const selectedValue = (event.target as HTMLSelectElement).value;
    switch (selectedValue) {
      case "1":
        this.filterByPriceLowToHigh();
        break;
      case "2":
        this.filterByPriceHighToLow();
        break;
      default:
        this.filterByPriceLowToHigh();
        break;
    }
  }
}
