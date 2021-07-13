import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MatSelectChange } from '@angular/material/select';
import { ISelectEntry, SelectConfig } from '../../models/select-config.model';
import { getSelectedEntry } from '../../utils/select-utils';

@Component({
  selector: 'app-basic-select',
  templateUrl: './basic-select.component.html',
  styleUrls: ['./basic-select.component.scss'],
})
export class BasicSelectComponent implements OnInit {
  /**
   * Required
   * Config for basic select
   */
  @Input()
  config: SelectConfig<ISelectEntry>;

  /**
   * Emits selected entry
   */
  @Output()
  selectionChange: EventEmitter<ISelectEntry> = new EventEmitter();

  constructor() {}

  ngOnInit(): void {}

  onSelectionChange(event: MatSelectChange): void {
    const selected = getSelectedEntry(this.config?.entries, event?.value);

    if (selected) {
      this.selectionChange.emit(selected);
    }
  }
}
