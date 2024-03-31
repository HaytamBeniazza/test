import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PetProductCardComponent } from './pet-product-card.component';

describe('PetProductCardComponent', () => {
  let component: PetProductCardComponent;
  let fixture: ComponentFixture<PetProductCardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PetProductCardComponent]
    });
    fixture = TestBed.createComponent(PetProductCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
