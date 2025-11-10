import { Component , OnInit} from '@angular/core';
import {Keynote, KeynoteService} from '../../services/keynote.service';


@Component({
  selector: 'app-keynotes',
  standalone: false,
  templateUrl: './keynotes.component.html',
  styleUrl: './keynotes.component.css'
})
export class KeynotesComponent implements OnInit {
  keynotes: Keynote[] = [];
  loading = true;
  errorMessage = '';

  constructor(private keynoteService: KeynoteService) {}

  ngOnInit(): void {
    this.keynoteService.getAllKeynotes().subscribe({
      next: (data) => {
        this.keynotes = data;
        this.loading = false;
      },
      error: (err) => {
        this.errorMessage = 'Erreur lors du chargement des keynotes';
        this.loading = false;
        console.error(err);
      }
    });
  }
}
