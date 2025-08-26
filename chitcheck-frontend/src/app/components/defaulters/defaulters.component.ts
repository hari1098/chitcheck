import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-defaulters',
  templateUrl: './defaulters.component.html',
  styleUrls: ['./defaulters.component.scss']
})
export class DefaultersComponent implements OnInit {
  defaultersForm: FormGroup;
  activeTab = 'personal';

  tabs = [
    { id: 'personal', label: 'Personal Details' },
    { id: 'group', label: 'Group Details' },
    { id: 'guarantor', label: 'Guarantor Details' },
    { id: 'bonds', label: 'Bonds Details' },
    { id: 'case', label: 'Case Details' },
    { id: 'status', label: 'Status' }
  ];

  constructor(private fb: FormBuilder) {
    this.defaultersForm = this.fb.group({
      subscriberName: ['', Validators.required],
      fatherName: ['abc'],
      area: [''],
      dateOfBirth: [''],
      address: [''],
      phoneNo: ['9842012345'],
      emailId: ['admin@chitcheck.com'],
      introducer: ['Kumar'],
      nominee: ['Kumar'],
      relationship: ['Father'],
      pancard: ['ABCTY1234D'],
      subscriberType: [''],
      aadharCard: ['1234-1234-1235'],
      gstNumber: ['33AAOFT3780G1ZX']
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
      'group': 'Manage group information and details here.',
      'guarantor': 'Manage guarantor information here.',
      'bonds': 'Manage bonds and securities information here.',
      'case': 'Manage legal case information here.',
      'status': 'View and update status information here.'
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
    if (this.defaultersForm.valid) {
      console.log('Form submitted:', this.defaultersForm.value);
      // Handle form submission here
    }
  }
}