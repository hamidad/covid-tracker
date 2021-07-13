export interface ISelectEntry {
  value: any;
  viewValue: string;
}

export interface SelectConfig<SelectEntry> {
  label?: string;
  selected?: SelectEntry;
  entries: SelectEntry[];
}

export class SelectEntry implements ISelectEntry {
  value: any;
  viewValue: string;

  constructor(value: any, viewValue: string) {
    this.value = value;
    this.viewValue = viewValue;
  }
}
