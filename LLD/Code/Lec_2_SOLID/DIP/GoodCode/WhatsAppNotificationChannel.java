package Lec_2_SOLID.DIP.GoodCode;

public class WhatsAppNotificationChannel implements NotificationChannel {

    /*
    - NotificationService depends on NotificationChannel (interface)
    - WhatsAppNotificationChannel implements NotificationChannel
    - Both depend on abstraction, not on each other
    - New channels can be added without modifying NotificationService
     */

    /*
     * LOW-LEVEL MODULE:
     * This class represents a concrete implementation
     * of the NotificationChannel abstraction.
     *
     * It follows DIP because:
     * - It DEPENDS on the abstraction (NotificationChannel)
     * - It does NOT depend on NotificationService
     *
     * NotificationService can use this class
     * without knowing it is WhatsApp.
     */

    @Override
    public void send(String msg) {

        /*
         * WhatsApp-specific message sending logic.
         * Can change independently without affecting
         * NotificationService.
         */
        System.out.println("Sending WhatsApp message: " + msg);
    }
}