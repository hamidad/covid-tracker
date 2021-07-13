import { isDefined } from 'src/app/shared/utils/shared-utils';
import { ISelectEntry } from '../models/select-config.model';

export function getSelectedEntry(
  entries: ISelectEntry[] = [],
  value: any
): ISelectEntry | undefined {
  if (!isDefined(entries) || !isDefined(value)) {
    return undefined;
  }

  if (entries.length === 0) {
    return undefined;
  }

  return entries?.find((entry: ISelectEntry) => {
    return entry?.value === value;
  });
}
