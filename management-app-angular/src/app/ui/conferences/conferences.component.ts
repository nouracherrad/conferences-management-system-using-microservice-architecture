import { Component, OnInit } from '@angular/core';
import {Conference, ConferenceService} from '../../services/conference.service';
import {DatePipe, NgForOf, NgIf} from '@angular/common';

@Component({
  selector: 'app-conferences',
  templateUrl: './conferences.component.html',
  imports: [
    DatePipe,
    NgForOf,
    NgIf
  ],
  styleUrls: ['./conferences.component.css']
})
export class ConferencesComponent implements OnInit {
  conferences: Conference[] = [];
  loading = true;
  errorMessage = '';

  constructor(private conferenceService: ConferenceService) {}

  ngOnInit(): void {
    this.conferenceService.getAllConferences().subscribe({
      next: (data) => {
        this.conferences = data;
        this.loading = false;
      },
      error: (err) => {
        this.errorMessage = 'Erreur lors du chargement des conférences';
        this.loading = false;
        console.error(err);
      }
    });
  }
  deleteConference(id: number): void {
    if (confirm('Voulez-vous vraiment supprimer cette conférence ?')) {
      this.conferenceService.deleteConference(id).subscribe({
        next: () => {
          this.conferences = this.conferences.filter(conf => conf.id !== id);
        },
        error: (err) => {
          console.error(err);
          this.errorMessage = "Erreur lors de la suppression de la conférence";
        }
      });
    }
  }

}
