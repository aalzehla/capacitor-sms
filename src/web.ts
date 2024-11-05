import { WebPlugin } from '@capacitor/core';
import {PermissionStatus, SmsManagerPlugin, SmsPluginPermissions, SmsSendOptions} from "./definitions";

export class SmsManagerPluginWeb extends WebPlugin implements SmsManagerPlugin {
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
