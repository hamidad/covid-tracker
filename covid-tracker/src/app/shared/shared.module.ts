import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from './material/material.module';
import { FORMS_COMPONENTS } from './forms';

@NgModule({
  declarations: [FORMS_COMPONENTS],
  imports: [CommonModule, FormsModule, ReactiveFormsModule, MaterialModule],
  exports: [FormsModule, ReactiveFormsModule, MaterialModule, FORMS_COMPONENTS],
})
export class SharedModule {}
