import { WebPlugin } from '@capacitor/core';

import {PermissionStatus, CapacitorSmsPlugin, SmsPluginPermissions, SmsSendOptions} from "./definitions";

export class CapacitorSmsWeb extends WebPlugin implements CapacitorSmsPlugin {
    checkPermissions(options: SmsPluginPermissions): Promise<PermissionStatus> {
        throw this.unimplemented('Not implemented on web.');
    }
    requestPermissions(options: SmsPluginPermissions): Promise<PermissionStatus> {
        throw this.unimplemented('Not implemented on web.');
    }

    async send(options: SmsSendOptions): Promise<void> {
        throw this.unimplemented('Not implemented on web.');
    }

}
