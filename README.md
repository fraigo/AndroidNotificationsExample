# AndroidNotificationsExample
Android Notifications Example with retro-compatibility (API 19+)


# Overview
This example shows how to buid and show Notifications using android Notifications API. 
The original Notification classes are for API 26+, but you can use NotificationCompat classes using the andropid support library.

In this example, a NotificationHelper class is created to help:
* Creating notification channels (Channel ID and Name) in compatibility mode (some properties are relevant only for newer API devices)
  * There are 2 pre-generated channels: DEFAULT and URGENT (in older devices, there is no difference)
* Creating and showing notifications (Title, message and channel) 
* Displaying the notification channel configuration using the channel ID.



