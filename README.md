# capacitor-sms

Send and Read SMS

## Install

```bash
npm install capacitor-sms
npx cap sync
```

## API

<docgen-index>

* [`send(...)`](#send)
* [`checkPermissions(...)`](#checkpermissions)
* [`requestPermissions(...)`](#requestpermissions)
* [Interfaces](#interfaces)
* [Type Aliases](#type-aliases)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### send(...)

```typescript
send(options: SmsSendOptions) => Promise<void>
```

Send the sms

| Param         | Type                                                      |
| ------------- | --------------------------------------------------------- |
| **`options`** | <code><a href="#smssendoptions">SmsSendOptions</a></code> |

--------------------


### checkPermissions(...)

```typescript
checkPermissions(options: SmsPluginPermissions) => Promise<PermissionStatus>
```

| Param         | Type                                                                  |
| ------------- | --------------------------------------------------------------------- |
| **`options`** | <code><a href="#smspluginpermissions">SmsPluginPermissions</a></code> |

**Returns:** <code>Promise&lt;<a href="#permissionstatus">PermissionStatus</a>&gt;</code>

--------------------


### requestPermissions(...)

```typescript
requestPermissions(options: SmsPluginPermissions) => Promise<PermissionStatus>
```

| Param         | Type                                                                  |
| ------------- | --------------------------------------------------------------------- |
| **`options`** | <code><a href="#smspluginpermissions">SmsPluginPermissions</a></code> |

**Returns:** <code>Promise&lt;<a href="#permissionstatus">PermissionStatus</a>&gt;</code>

--------------------


### Interfaces


#### SmsSendOptions

| Prop          | Type                  |
| ------------- | --------------------- |
| **`numbers`** | <code>string[]</code> |
| **`text`**    | <code>string</code>   |


#### PermissionStatus

| Prop          | Type                                                        |
| ------------- | ----------------------------------------------------------- |
| **`send`**    | <code><a href="#permissionstate">PermissionState</a></code> |
| **`receive`** | <code><a href="#permissionstate">PermissionState</a></code> |
| **`read`**    | <code><a href="#permissionstate">PermissionState</a></code> |


#### SmsPluginPermissions

| Prop             | Type                                                            |
| ---------------- | --------------------------------------------------------------- |
| **`permission`** | <code><a href="#smspermissiontype">SmsPermissionType</a></code> |


### Type Aliases


#### PermissionState

<code>'prompt' | 'prompt-with-rationale' | 'granted' | 'denied'</code>


#### SmsPermissionType

<code>'send' | 'receive' | 'read'</code>

</docgen-api>
