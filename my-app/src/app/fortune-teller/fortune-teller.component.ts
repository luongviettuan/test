import { Component, ChangeDetectorRef } from '@angular/core';
import { FortuneTellerService } from './fortune-teller.service';

@Component({
  selector: 'app-fortune-teller',
  templateUrl: './fortune-teller.component.html',
  styleUrl: './fortune-teller.component.scss'
})
export class FortuneTellerComponent {
  constructor(private fortuneTellerService : FortuneTellerService,
              private cd : ChangeDetectorRef) {}

  showBtnReload = false;
  ngOnInit() : void {
    this.fortuneTellerService.subscribeToFortuneTeller().subscribe({
      next: (fortune) => {
        this.showBtnReload = true;
        this.cd.detectChanges();
      },
      error: (err) => {
        console.log(err);
      }
    });
  }


  callapi() {
    this.fortuneTellerService.callApi().toPromise().then(data => console.log(data))
  }

  reloadPage() {
    location.reload();
  }
}
