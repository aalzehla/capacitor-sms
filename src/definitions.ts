import { PermissionState } from "@capacitor/core";
export interface SmsSendOptions {
    numbers: string[];
    text: string;
}

export declare type SmsPermissionType = 'send' | 'receive' | 'read';

export interface SmsPluginPermissions {
    permission: SmsPermissionType;
}

export interface PermissionStatus {
    send: PermissionState;
    receive: PermissionState;
    read: PermissionState;
}

export interface SmsManagerPlugin {
    /**
     * Send the sms
     * @param {SmsSendOptions} options
     * @returns {Promise<void>}
     */
    send(options: SmsSendOptions): Promise<void>;
    checkPermissions(options: SmsPluginPermissions): Promise<PermissionStatus>;
    requestPermissions(options: SmsPluginPermissions): Promise<PermissionStatus>;
}