import { Component, OnInit } from '@angular/core';

interface SearchResult {
  id: number;
  name: string;
  panCard: string;
  aadharCard: string;
  mobile: string;
  email: string;
  status: string;
}

@Component({
  selector: 'app-chit-check',
  templateUrl: './chit-check.component.html',
  styleUrls: ['./chit-check.component.scss']
})
export class ChitCheckComponent implements OnInit {
  searchCriteria = {
    name: '',
    pan: '',
    aadhar: '',
    mobile: ''
  };

  searchResults: SearchResult[] = [];
  searchPerformed = false;

  // Mock data for demonstration
  mockData: SearchResult[] = [
    {
      id: 1,
      name: 'John Doe',
      panCard: 'ABCTY1234D',
      aadharCard: '1234-1234-1235',
      mobile: '9842012345',
      email: 'john@example.com',
      status: 'Defaulter'
    },
    {
      id: 2,
      name: 'Jane Smith',
      panCard: 'XYZAB5678E',
      aadharCard: '5678-5678-5679',
      mobile: '9876543210',
      email: 'jane@example.com',
      status: 'Active'
    },
    {
      id: 3,
      name: 'Kumar Patel',
      panCard: 'DEFGH9012F',
      aadharCard: '9012-9012-9013',
      mobile: '9123456789',
      email: 'kumar@example.com',
      status: 'Pending'
    }
  ];

  constructor() { }

  ngOnInit(): void {
    // Initialize component
  }

  onSearch(): void {
    this.searchPerformed = true;
    
    // Filter mock data based on search criteria
    this.searchResults = this.mockData.filter(item => {
      const nameMatch = !this.searchCriteria.name || 
        item.name.toLowerCase().includes(this.searchCriteria.name.toLowerCase());
      const panMatch = !this.searchCriteria.pan || 
        item.panCard.toLowerCase().includes(this.searchCriteria.pan.toLowerCase());
      const aadharMatch = !this.searchCriteria.aadhar || 
        item.aadharCard.includes(this.searchCriteria.aadhar);
      const mobileMatch = !this.searchCriteria.mobile || 
        item.mobile.includes(this.searchCriteria.mobile);

      return nameMatch && panMatch && aadharMatch && mobileMatch;
    });
  }

  onClear(): void {
    this.searchCriteria = {
      name: '',
      pan: '',
      aadhar: '',
      mobile: ''
    };
    this.searchResults = [];
    this.searchPerformed = false;
  }

  viewDetails(result: SearchResult): void {
    console.log('Viewing details for:', result);
    // Implement view details functionality
  }
}