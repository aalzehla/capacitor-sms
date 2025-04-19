import { registerPlugin } from '@capacitor/core';

import type { CapacitorSmsPlugin } from './definitions';

const CapacitorSms = registerPlugin<CapacitorSmsPlugin>('CapacitorSms', {
  web: () => import('./web').then(m => new m.CapacitorSmsWeb()),
});

export * from './definitions';
export { CapacitorSms };