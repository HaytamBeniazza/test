import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PetProductTableComponent } from './pet-product-table.component';

describe('PetProductTableComponent', () => {
  let component: PetProductTableComponent;
  let fixture: ComponentFixture<PetProductTableComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PetProductTableComponent]
    });
    fixture = TestBed.createComponent(PetProductTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
