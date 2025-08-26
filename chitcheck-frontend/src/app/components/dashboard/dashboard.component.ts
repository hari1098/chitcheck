import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  totalDefaulters = 156;
  activeChits = 89;
  totalAmount = 2500000;
  pendingCases = 23;

  recentActivities = [
    {
      icon: '👤',
      text: 'New defaulter added: John Doe',
      time: '2 hours ago'
    },
    {
      icon: '💰',
      text: 'Payment received from ABC Company',
      time: '4 hours ago'
    },
    {
      icon: '📄',
      text: 'Case status updated for XYZ Ltd',
      time: '6 hours ago'
    },
    {
      icon: '✓',
      text: 'Chit group completed successfully',
      time: '1 day ago'
    }
  ];

  constructor() { }

  ngOnInit(): void {
    // Initialize component
  }
}