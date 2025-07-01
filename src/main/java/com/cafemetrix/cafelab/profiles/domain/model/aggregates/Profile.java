package com.cafemetrix.cafelab.profiles.domain.model.aggregates;

import com.cafemetrix.cafelab.profiles.domain.model.commands.CreateProfileCommand;
import com.cafemetrix.cafelab.profiles.domain.model.valueobjects.EmailAddress;
import com.cafemetrix.cafelab.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;


/**
 * Profile Aggregate Root
 */
@Entity
public class Profile extends AuditableAbstractAggregateRoot<Profile> {
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "address", column = @Column(name = "email_address"))})
    private EmailAddress emailAddress;

    private String name;
    private String password;
    private String role;
    private String cafeteriaName;
    private String experience;
    private String profilePicture;
    private String paymentMethod;
    private boolean isFirstLogin;
    private String plan;
    private boolean hasPlan;

    /**
     * Constructor with all fields
     */
    public Profile(String name, String email, String password, String role, String cafeteriaName, String experience, String profilePicture, String paymentMethod, boolean isFirstLogin, String plan, boolean hasPlan) {
        this.name = name;
        this.emailAddress = new EmailAddress(email);
        this.password = password;
        this.role = role;
        this.cafeteriaName = cafeteriaName;
        this.experience = experience;
        this.profilePicture = profilePicture;
        this.paymentMethod = paymentMethod;
        this.isFirstLogin = isFirstLogin;
        this.plan = plan;
        this.hasPlan = hasPlan;
    }

    /**
     * Default constructor
     */
    public Profile() {
        this.emailAddress = new EmailAddress();
    }

    /**
     * Constructor with a CreateProfileCommand WITHOUT PASSWORD
     */
    public Profile(CreateProfileCommand command) {
        this.name = command.name();
        this.emailAddress = new EmailAddress(command.email());
        this.role = command.role();
        this.cafeteriaName = command.cafeteriaName();
        this.experience = command.experience();
        this.profilePicture = command.profilePicture();
        this.paymentMethod = command.paymentMethod();
        this.isFirstLogin = command.isFirstLogin();
        this.plan = command.plan();
        this.hasPlan = command.hasPlan();
    }

    /**
     * Constructor with without password
     */
    public Profile(String name, String email, String role, String cafeteriaName, String experience, String profilePicture, String paymentMethod, boolean isFirstLogin, String plan, boolean hasPlan) {
        this.name = name;
        this.emailAddress = new EmailAddress(email);
        this.role = role;
        this.cafeteriaName = cafeteriaName;
        this.experience = experience;
        this.profilePicture = profilePicture;
        this.paymentMethod = paymentMethod;
        this.isFirstLogin = isFirstLogin;
        this.plan = plan;
        this.hasPlan = hasPlan;
    }

    /**
     * Email address getter
     * @return Email address
     */
    public String getEmailAddress() {
        return emailAddress.address();
    }

    /**
     * Update profile name
     */
    public void updateName(String name) {
        this.name = name;
    }

    /**
     * Update profile email address
     */
    public void updateEmailAddress(String email) {
        this.emailAddress = new EmailAddress(email);
    }

    /**
     * Update profile password
     */
    public void updatePassword(String password) {
        this.password = password;
    }

    /**
     * Update profile role
     */
    public void updateRole(String role) {
        this.role = role;
    }

    /**
     * Update profile cafeteria name
     */
    public void updateCafeteriaName(String cafeteriaName) {
        this.cafeteriaName = cafeteriaName;
    }

    /**
     * Update profile experience
     */
    public void updateExperience(String experience) {
        this.experience = experience;
    }

    /**
     * Update profile picture
     */
    public void updateProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    /**
     * Update profile payment method
     */
    public void updatePaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * Update profile plan
     */
    public void updatePlan(String plan) {
        this.plan = plan;
    }

    /**
     * Update profile first login status
     */
    public void updateFirstLoginStatus(boolean isFirstLogin) {
        this.isFirstLogin = isFirstLogin;
    }

    /**
     * Update profile has plan status
     */
    public void updateHasPlanStatus(boolean hasPlan) {
        this.hasPlan = hasPlan;
    }

    /**
     * Get profile name
     */
    public String getName() {
        return name;
    }

    /**
     * Get profile password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Get profile role
     */
    public String getRole() {
        return role;
    }

    /**
     * Get profile cafeteria name
     */
    public String getCafeteriaName() {
        return cafeteriaName;
    }

    /**
     * Get profile experience
     */
    public String getExperience() {
        return experience;
    }

    /**
     * Get profile picture
     */
    public String getProfilePicture() {
        return profilePicture;
    }

    /**
     * Get profile payment method
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Get profile first login status
     */
    public boolean isFirstLogin() {
        return isFirstLogin;
    }

    /**
     * Get profile plan
     */
    public String getPlan() {
        return plan;
    }

    /**
     * Get profile has plan status
     */
    public boolean hasPlan() {
        return hasPlan;
    }

}
