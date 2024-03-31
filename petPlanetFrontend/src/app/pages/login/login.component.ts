import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/services/auth/auth.service';
import { trigger, state, style, animate, transition } from '@angular/animations';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  animations: [
    trigger('expand', [
      state('hidden', style({
        height: '0',
        opacity: '0',
        overflow: 'hidden'
      })),
      state('visible', style({
        height: '*',
        opacity: '1'
      })),
      transition('hidden <=> visible', animate('300ms ease-in-out'))
    ])
  ]
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;
  ClientRegisterForm!: FormGroup;
  BlogAuthorRegisterForm!: FormGroup;
  roleForm!: FormGroup;
  ClientRole: boolean = true;
  isRegisterOpen = false;

  toggleRegister(): void {
    this.isRegisterOpen = !this.isRegisterOpen;
  }

  constructor(private authService: AuthService, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.initializeForms();
  }

  initializeForms() {
    this.loginForm = this.fb.group({
      email: this.fb.control('', [Validators.required]),
      password: this.fb.control('', [Validators.required]),
    });
    this.roleForm = this.fb.group({
      role: this.fb.control('applicant', [Validators.required]),
    });
    this.ClientRegisterForm = this.fb.group({
      fullName: this.fb.control('', [Validators.required]),
      email: this.fb.control('', [Validators.required, Validators.email]),
      password: this.fb.control('', [Validators.required, Validators.email]),
      address: this.fb.control('', [Validators.required]),
      phone: this.fb.control('', [Validators.required]),
    });
    this.BlogAuthorRegisterForm = this.fb.group({
      fullName: this.fb.control('', [Validators.required]),
      email: this.fb.control('', [Validators.required, Validators.email]),
      password: this.fb.control('', [Validators.required, Validators.email]),
    });
  }


  roleChoice() {
    if (this.roleForm.get('role')!.value === 'client') {
      this.ClientRole = true;
      this.ClientRegisterForm.reset();
      this.ClientRegisterForm.patchValue({
        fullName: '',
        email: '',
        password: '',
        address: '',
        phone: '',
      });
      this.ClientRegisterForm.markAsUntouched();
      this.ClientRegisterForm.markAsPristine();
    } else {
      this.ClientRole = false;
      this.BlogAuthorRegisterForm.reset();
      this.BlogAuthorRegisterForm.patchValue({
        name: '',
        email: '',
        password: '',
      });
      this.BlogAuthorRegisterForm.markAsUntouched();
      this.BlogAuthorRegisterForm.markAsPristine();
    }
  }


  login() {
    const loginData = {
      email: this.loginForm.value.email,
      password: this.loginForm.value.password,
    };
    this.authService.authenticate(loginData);
  }

  register() {
    var user = {}
    if(this.ClientRole == true){
      user = {
        fullName: this.ClientRegisterForm.value.fullName,
        email: this.ClientRegisterForm.value.email,
        password: this.ClientRegisterForm.value.password,
        address: this.ClientRegisterForm.value.address,
        phone: this.ClientRegisterForm.value.phone,
        role: 'CLIENT',
      };
    }else{
      user ={
        fullName: this.ClientRegisterForm.value.fullName,
        email: this.ClientRegisterForm.value.email,
        password: this.ClientRegisterForm.value.password,
        role: 'BLOGAUTHOR',
      }
    }
    this.authService.register(user);
  }
}
