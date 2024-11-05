package com.aalzehla.capacitor.sms;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.telephony.SmsManager;
import android.util.Log;

import androidx.activity.result.ActivityResult;
import androidx.core.app.ActivityCompat;

import com.getcapacitor.JSArray;
import com.getcapacitor.JSObject;
import com.getcapacitor.PermissionState;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.ActivityCallback;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.annotation.Permission;
import com.getcapacitor.annotation.PermissionCallback;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

@CapacitorPlugin(
    name = "SmsManager",
    permissions = {
        @Permission(strings = { Manifest.permission.SEND_SMS }, alias = SmsManagerPlugin.SEND_SMS),
        @Permission(strings = { Manifest.permission.RECEIVE_SMS }, alias = SmsManagerPlugin.RECEIVE_SMS),
        @Permission(strings = { Manifest.permission.READ_SMS }, alias = SmsManagerPlugin.READ_SMS)
    }
)
public class SmsManagerPlugin extends Plugin {

    static final String SEND_SMS = "send";
    static final String RECEIVE_SMS = "receive";
    static final String READ_SMS = "read";

    private static final String BASE_LOG_TAG = "com.aalzehla.sms";
    private static final String ERR_SERVICE_NOTFOUND = "ERR_SERVICE_NOTFOUND";
    private static final String ERR_NO_NUMBERS = "ERR_NO_NUMBERS";
    private static final String ERR_NO_TEXT = "ERR_NO_TEXT";
    private static final String SEND_CANCELLED = "SEND_CANCELLED";

    public SmsManagerPlugin() {}

    @PluginMethod()
    public void send(final PluginCall call) {
        sendSMS(call);
    }

    private void sendSMS(final PluginCall call) {
        JSArray numberArray = call.getArray("numbers");
        List<String> recipientNumbers = null;
        try {
            recipientNumbers = numberArray.toList();
        } catch (JSONException e) {
            Log.e(getLogTag(BASE_LOG_TAG), "'numbers' json structure not parsable", e);
        }

        if (recipientNumbers == null || recipientNumbers.isEmpty()) {
            call.reject(ERR_NO_NUMBERS);
            return;
        }

        String text = ConfigUtils.getCallParam(String.class, call, "text");
        if (text == null || text.length() == 0) {
            call.reject(ERR_NO_TEXT);
            return;
        }

        // Check for SEND_SMS permission
        if (getPermissionState(SEND_SMS) != PermissionState.GRANTED) {
            call.reject("Permission SEND_SMS not granted");
            return;
        }

        SmsManager smsManager = SmsManager.getDefault();
        for (String number : recipientNumbers) {
            try {
                smsManager.sendTextMessage(number, null, text, null, null);
            } catch (Exception e) {
                Log.e(getLogTag(BASE_LOG_TAG), "Failed to send SMS to " + number, e);
                call.reject("Failed to send SMS to " + number);
                return;
            }
        }

        call.resolve();
    }

    @ActivityCallback
    private void onSmsRequestResult(PluginCall call, ActivityResult result) {
        if (result.getResultCode() == Activity.RESULT_CANCELED) {
            call.reject(SEND_CANCELLED);
        } else {
            call.resolve();
        }
    }

    private String getJoinedNumbers(List<String>numbers, String separator) {
        StringBuilder joined = new StringBuilder();
        for (int i = 0; i < numbers.size(); i++) {
            if (i > 0) {
                joined.append(separator);
            }
            joined.append(numbers.get(i));
        }
        return joined.toString();
    }
    @PluginMethod
    public void checkPermissions(PluginCall call) {
        String permission = call.getString("permission");
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            JSObject permissionsResultJSON = new JSObject();
            permissionsResultJSON.put(permission, "granted");
            call.resolve(permissionsResultJSON);
        } else {
            super.checkPermissions(call);
        }
    }

    @PluginMethod
    public void requestPermissions(PluginCall call) {
        String permission = call.getString("permission");
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU || getPermissionState(permission) == PermissionState.GRANTED) {
            JSObject permissionsResultJSON = new JSObject();
            permissionsResultJSON.put(permission, "granted");
            call.resolve(permissionsResultJSON);
        } else {
            requestPermissionForAlias(permission, call, "permissionsCallback");
        }
    }
    @PermissionCallback
    private void permissionsCallback(PluginCall call) {
        this.checkPermissions(call);
    }


}
