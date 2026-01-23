package Lec_2_SOLID.DIP.GoodCode;

public class NotificationService {

    /*
     * GOOD DIP DESIGN:
     * High-level module (NotificationService)
     * depends on an ABSTRACTION (NotificationChannel),
     * not on concrete implementations.
     *
     * Low-level modules (Email, SMS, WhatsApp, etc.)
     * will implement this abstraction.
     */

    private NotificationChannel notificationChannel; // abstraction, not concrete class

    /*
     * Dependency Injection:
     * The dependency is PROVIDED from outside
     * instead of being created inside the class.
     *
     * Benefits:
     * - Loose coupling
     * - Easy testing (mock NotificationChannel)
     * - Easy extension (new channels without modifying this class)
     */
    public NotificationService(NotificationChannel channel){
        this.notificationChannel = channel;
    }

    /*
     * High-level business logic depends only on the abstraction.
     * NotificationService does not care HOW the message is sent,
     * only THAT it is sent.
     *
     * This fully satisfies DIP.
     */
    public void notify(String msg){
        notificationChannel.send(msg);
    }
}
