import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
// Angular Materials
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { CdkStepperModule, MAT_STEPPER_GLOBAL_OPTIONS } from '@angular/cdk/stepper';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatListModule } from '@angular/material/list';
import {MatAutocompleteModule} from '@angular/material/autocomplete';

// tslint:disable-next-line:max-line-length
import {
  MatChipsModule, MatTableModule, MatExpansionModule, MatInputModule, MatSelectModule,
  MatDatepickerModule, MatNativeDateModule, MatSortModule, MatPaginatorModule,
  MatButtonToggleModule, MatStepperModule, MatFormFieldModule, MatRadioModule,
  MatCheckboxModule, MatGridListModule, MatDialogModule, MatSnackBarModule,
  MatCardModule, MatProgressBarModule, MatTooltipModule, MatProgressSpinnerModule
} from '@angular/material';
import { MatMenuModule } from '@angular/material/menu';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { CdkTableModule } from '@angular/cdk/table';
// import { DraggableDirective } from './draggable.directive';

@NgModule({
  declarations: [],
  imports: [CommonModule],
  exports: [
    CommonModule,
    MatMenuModule,
    FontAwesomeModule,
    NgbModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatAutocompleteModule,
    FormsModule,
    MatSidenavModule,
    MatChipsModule,
    MatIconModule,
    CdkStepperModule,
    MatToolbarModule,
    MatButtonModule,
    MatTableModule,
    MatListModule,
    MatExpansionModule,
    MatInputModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSortModule,
    MatPaginatorModule,
    MatButtonToggleModule,
    MatStepperModule,
    MatFormFieldModule,
    MatRadioModule,
    MatCheckboxModule,
    MatGridListModule,
    CommonModule,
    HttpClientModule,
    MatIconModule,
    MatDialogModule,
    MatSidenavModule,
    MatToolbarModule,
    MatButtonModule,
    MatListModule,
    MatTableModule,
    MatMenuModule,
    ReactiveFormsModule,
    MatInputModule,
    MatSortModule,
    MatPaginatorModule,
    FormsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSnackBarModule,
    NgbModule,
    MatButtonToggleModule,
    MatStepperModule,
    MatFormFieldModule,
    MatRadioModule,
    MatCheckboxModule,
    MatSelectModule,
    MatGridListModule,
    CdkStepperModule,
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    MatCardModule,
    MatProgressBarModule,
    MatExpansionModule,
    CdkTableModule,
    MatTooltipModule,
    CommonModule,
    MatIconModule,
    MatListModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatButtonModule,
    MatInputModule,
    MatDialogModule,
    MatExpansionModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    ReactiveFormsModule,
    FormsModule,
    MatToolbarModule,
    MatExpansionModule,
    MatInputModule,
    MatSelectModule,
    MatChipsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatNativeDateModule,
    MatAutocompleteModule,
    FontAwesomeModule,
    MatProgressSpinnerModule,
  ]
})
export class SharedModule { }
