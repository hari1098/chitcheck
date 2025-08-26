import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  profileForm: FormGroup;
  activeTab = 'company';

  tabs = [
    { id: 'company', label: 'Company Details' },
    { id: 'directors', label: 'Directors Details' },
    { id: 'proofs', label: 'Proofs Details' },
    { id: 'user', label: 'User Details' },
    { id: 'status', label: 'Status' }
  ];

  constructor(private fb: FormBuilder) {
    this.profileForm = this.fb.group({
      companyName: ['mobifish', Validators.required],
      contactNo: ['9942728162', Validators.required],
      email: ['support@mobifish.in', [Validators.required, Validators.email]],
      address: [''],
      contactPerson: ['Chit Check'],
      pancard: ['ABCTY1234D'],
      cin: [''],
      gstNumber: ['']
    });
  }

  ngOnInit(): void {
    // Initialize component
  }

  setActiveTab(tabId: string): void {
    this.activeTab = tabId;
  }

  getTabTitle(): string {
    const tab = this.tabs.find(t => t.id === this.activeTab);
    return tab ? tab.label : '';
  }

  getTabDescription(): string {
    const descriptions: { [key: string]: string } = {
      'directors': 'Manage company directors and their details here.',
      'proofs': 'Upload and manage company proof documents here.',
      'user': 'Manage user account details and preferences here.',
      'status': 'View account status and verification information here.'
    };
    return descriptions[this.activeTab] || '';
  }

  onFileSelect(event: any, type: string): void {
    const file = event.target.files[0];
    if (file) {
      const fileStatus = event.target.parentElement?.querySelector('.file-status');
      if (fileStatus) {
        fileStatus.textContent = file.name;
      }
    }
  }

  onSubmit(): void {
    if (this.profileForm.valid) {
      console.log('Form submitted:', this.profileForm.value);
      // Handle form submission here
    }
  }
}