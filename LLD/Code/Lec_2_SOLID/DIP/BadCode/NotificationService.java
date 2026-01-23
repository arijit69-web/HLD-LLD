package Lec_2_SOLID.DIP.BadCode;

public class NotificationService {
    // DIP is violated when high-level modules depend directly on low-level concrete implementations instead of abstractions.

    /*
     * DIP VIOLATION:
     * High-level module (NotificationService)
     * directly depends on low-level concrete classes
     * (EmailService and SMSService).
     *
     * This creates TIGHT COUPLING.
     */

    private EmailService emailService; // concrete dependency
    private SMSService smsService;     // concrete dependency

    /*
     * DIP VIOLATION:
     * NotificationService itself creates the objects.
     * It controls WHICH implementation to use.
     *
     * Problems:
     * - Hard to test (cannot mock)
     * - Hard to extend (adding WhatsApp requires modifying this class)
     * - Breaks Open/Closed Principle
     */
    public NotificationService(){
        this.emailService = new EmailService();
        this.smsService = new SMSService();
    }

    /*
     * High-level logic depends directly on EmailService.
     * Any change in EmailService affects NotificationService.
     */
    public void notifyByEmail(String msg){
        emailService.sendEmail(msg);
    }

    /*
     * High-level logic depends directly on SMSService.
     * Not flexible or scalable.
     */
    public void notifyBySMS(String msg){
        smsService.sendSMS(msg);
    }
}
