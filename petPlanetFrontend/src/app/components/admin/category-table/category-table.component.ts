import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CategoryService } from 'src/app/services/category/category.service';
import { PopupComponent } from '../../popup/popup.component';

@Component({
  selector: 'app-category-table',
  templateUrl: './category-table.component.html',
  styleUrls: ['./category-table.component.scss'],
})
export class CategoryTableComponent implements OnInit {
  categories: any[] = [];
  categoryForm!: FormGroup;
  @ViewChild(PopupComponent) popupComponent!: PopupComponent;


  constructor(
    private categoryService: CategoryService,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.getCategories();
    this.initializeCategoryForm();
  }

  getCategories(): void {
    this.categoryService.getAllCategories(0, 10).subscribe((categories) => {
      this.categories = categories;
    });
  }

  deleteCategory(categoryId: number): void {
    this.categoryService.deleteCategory(categoryId).subscribe(() => {
      this.categories = this.categories.filter(
        (category) => category.id !== categoryId
      );
    });
  }

  initializeCategoryForm(): void {
    this.categoryForm = this.formBuilder.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
    });
  }

  addCategory(): void {
    if (this.categoryForm.valid) {
      const newCategory: any = this.categoryForm.value;
      this.categoryService
        .addCategory(newCategory)
        .subscribe((category: any) => {
          this.getCategories();
          this.categoryForm.reset();
          this.popupComponent.Toggle();
        });
    } else {
      console.error('Form is invalid');
    }
  }
}
