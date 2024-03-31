import { Component, Input } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { Order } from 'src/app/Interfaces/OrderItem';
import { Pet } from 'src/app/Interfaces/Pet';
import { PetProduct } from 'src/app/Interfaces/PetProduct';
import { OrderService } from 'src/app/services/order/order.service';
import { PetProductService } from 'src/app/services/petProduct/pet-product.service';

@Component({
  selector: 'app-pet-product-card',
  templateUrl: './pet-product-card.component.html',
  styleUrls: ['./pet-product-card.component.scss']
})
export class PetProductCardComponent {
  @Input() link: string | undefined;

  constructor(private orderService: OrderService, private petProductService: PetProductService,private sanitizer: DomSanitizer) {}


  ngOnInit(): void {
    console.log(typeof(this.petProduct))
  }

  @Input() petProduct: PetProduct | undefined;
  getImageUrl(companyImage:any) {
    const imageUrl = `data:${companyImage.type};base64,${companyImage.data}`;
    return this.sanitizer.bypassSecurityTrustUrl(imageUrl);
  }


  // initializeOrder(clientId: number, petProduct: PetProduct): Order {
  //   // const order: Order = {
  //     // client: clientId, // set client ID from the provided value
  //     // orderLines: [
  //     //   {
  //     //     id: 55, // add id property
  //     //     dateTime: new Date(), // add dateTime property
  //     //     petProduct: {
  //     //       id: petProduct.id, // set petProduct ID
  //     //       product: petProduct.product, // set product from the provided petProduct
  //     //       pet: petProduct.pet, // set pet from the provided petProduct
  //     //       category: petProduct.category,
  //     //       review: []
  //     //     }, // set petProductId from the provided petProduct
  //     //     quantity: 1 // set the quantity (you can adjust this as needed)
  //     //   }
  //     // ],
  //     // status: 'Pending', // default status
  //     // total: 0 // initialize total to 0 (you can adjust this as needed)
  //   };

  //   // return order;
  

  // }

  // Method to handle the "Order" button click
  // onClickOrder(petProduct: PetProduct | undefined): void {
  //   if (petProduct) {
  //     // Initialize a new Order object
  //     const order: Order = this.initializeOrder(petProduct);

  //     // Call the saveOrder method
  //     this.saveOrder(order);
  //   }
  // }

  // Method to save the order
  // saveOrder(order: Order): void {
  //   console.log(order);
  //   this.orderService.add(order).subscribe(
  //     data => {
  //       console.log(data);
  //       this.ngOnInit();
  //     },
  //     error => {
  //       console.error('Error saving order:', error);
  //     }
  //   );
  // }
}
