import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Product } from 'src/app/Interfaces/Product';
import { ProductService } from 'src/app/services/product/product.service';
import { PopupComponent } from '../../popup/popup.component';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-product-table',
  templateUrl: './product-table.component.html',
  styleUrls: ['./product-table.component.scss']
})
export class ProductTableComponent implements OnInit {
  Products: Product[] = [];
  productForm!: FormGroup;
  @ViewChild(PopupComponent) popupComponent!: PopupComponent;

  constructor(
    private productService: ProductService,
    private formBuilder: FormBuilder,
    private sanitizer: DomSanitizer
  ) { }

  ngOnInit(): void {
    this.getProducts();
    this.initializeProductForm();
  }

  getProducts(): void {
    this.productService.getAllProducts(0, 10)
      .subscribe(products => {
        this.Products = products;
        console.log('====================================');
        console.log(this.Products,'pr :',products);
        console.log('====================================');
      });
  }

  deleteProduct(productId: number): void {
    this.productService.deleteProduct(productId)
      .subscribe(() => {
        this.Products = this.Products.filter(product => product.id !== productId);
      });
  }
  
  onFileChange(event: any) {
    const file = event.target.files[0];
    this.productForm.patchValue({ image: file });
  }
  getImageUrl(companyImage:any) {
    const imageUrl = `data:${companyImage.type};base64,${companyImage.data}`;
    return this.sanitizer.bypassSecurityTrustUrl(imageUrl);
  }

  initializeProductForm(): void {
    this.productForm = this.formBuilder.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      quantity: ['', Validators.required],
      price: ['', Validators.required],
      image: [null, Validators.required],
    });
  }

  addProduct(): void {
    if (this.productForm.valid) {
      const newProduct = {
        name: this.productForm.get('name')!.value,
        description: this.productForm.get('description')!.value,
        quantity: this.productForm.get('quantity')!.value,
        price: this.productForm.get('price')!.value,
        image: this.productForm.get('image')!.value,
      };

      this.productService.addProduct(newProduct)
        .subscribe((product: Product) => {
          this.getProducts();
          this.productForm.reset();
          this.popupComponent.Toggle();
        });
    } else {
      console.error('Invalid form submission');
    }
  }
}
