import { Component, Input } from '@angular/core';
import { Pet } from 'src/app/Interfaces/Pet';

@Component({
  selector: 'app-pet-card',
  templateUrl: './pet-card.component.html',
  styleUrls: ['./pet-card.component.scss']
})
export class PetCardComponent {

  ngOnInit(): void {
    console.log(typeof(this.pet))
  }
  @Input() pet: Pet | undefined;

}
