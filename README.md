# AndroidNotificationsExample
Android Notifications Example with retro-compatibility (API 19+)


# Overview
This example shows how to buid and show Notifications using android Notifications API. 
The original `Notification` API is compatible with API 26+ devices, but you can use `NotificationCompat` classes using the android support libraries (`com.android.support:support-v4`)

In this example, a `NotificationHelper` class is created to help:
* Creating notification channels (Channel ID and Name) in compatibility mode (some properties are relevant only for newer API devices)
  * There are 2 pre-generated channels: `DEFAULT_CHANNEL` and `URGENT_CHANNEL` (in older devices, there is no difference)
* Creating and showing notifications (Title, message and channel) 
* Displaying the notification channel configuration using the channel ID.


# Usage

Instance of NotificationHelper:

```java
NotificationHelper notifications = new NotificationHelper(this);
```

Create a simple notification using a registered channel

```java
notifications.notify(1001, "Title", "General Message", notifications.DEFAULT_CHANNEL);
notifications.notify(1002, "Title", "Urgent Message", notifications.URGENT_CHANNEL);
```

Calling the notification channel configurator

```java
notifications.showNotificationSettings(this, notifications.URGENT_CHANNEL);
```






