export interface SmsManagerPlugin {
    /**
     * Send the sms
     * @param {SmsSendOptions} options
     * @returns {Promise<void>}
     */
    send(options: SmsSendOptions): Promise<void>;
    sendB(options: SmsSendOptions): Promise<void>;

}

export interface SmsSendOptions {
    numbers: string[];
    text: string;
}
