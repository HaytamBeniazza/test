import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PetProduct } from 'src/app/Interfaces/PetProduct';
import { PetProductService } from 'src/app/services/petProduct/pet-product.service';

@Component({
  selector: 'app-form-component',
  templateUrl: './form-component.component.html',
  styleUrls: ['./form-component.component.scss']
})
export class FormComponentComponent {
  petProductForm: FormGroup;
  @Input() petProduct: PetProduct = {} as PetProduct;
  constructor(private fb: FormBuilder, private petProductService: PetProductService) {
    this.petProductForm = this.fb.group({
      pet: [null, Validators.required],
      startTime: [null, Validators.required],
      endTime: [null, Validators.required],
      numberOfParticipants: [null, Validators.required],
      location: [null, Validators.required],
      amount: [null, Validators.required],
    }, {
      // validators: [this.timeValidator]
    });
  }

}
