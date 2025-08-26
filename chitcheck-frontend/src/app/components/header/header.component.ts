import { Component } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {
  showUserMenu = false;

  constructor() { }

  toggleMenu(): void {
    // Toggle mobile menu functionality
    console.log('Menu toggled');
  }

  toggleUserMenu(): void {
    this.showUserMenu = !this.showUserMenu;
  }
}