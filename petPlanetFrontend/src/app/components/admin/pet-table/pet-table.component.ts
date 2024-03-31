import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PetService } from 'src/app/services/pet/pet.service';
import { PopupComponent } from '../../popup/popup.component';

@Component({
  selector: 'app-pet-table',
  templateUrl: './pet-table.component.html',
  styleUrls: ['./pet-table.component.scss']
})
export class PetTableComponent implements OnInit {
  pets: any[] = [];
  petForm!: FormGroup;
  @ViewChild(PopupComponent) popupComponent!: PopupComponent;


  constructor(
    private petService: PetService,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.getpets();
    this.initializePetForm();
  }

  getpets(): void {
    this.petService.getAllPets(0, 10)
      .subscribe(pets => {
        this.pets = pets;
      });
  }

  deletePet(petId: number): void {
    this.petService.deletePet(petId)
      .subscribe(() => {
        this.pets = this.pets.filter(pet => pet.id !== petId);
      });
  }

  initializePetForm(): void {
    this.petForm = this.formBuilder.group({
      name: ['', Validators.required],
      description: ['', Validators.required]
    });
  }

  addPet(): void {
    if (this.petForm.valid) {
      const newPet: any = this.petForm.value;
      this.petService.addPet(newPet)
        .subscribe((pet: any) => {
          this.getpets();
          this.petForm.reset();
          this.popupComponent.Toggle();
        });
    } else {
      console.error('Form is invalid');
    }
  }
}
