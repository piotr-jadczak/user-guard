package com.pj.userguard.user.field;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
public class AccountState {

    @Column(name = "is_locked")
    private boolean isLocked;

    @Column(name = "is_enabled")
    private boolean isEnabled;

    @Column(name = "is_expired")
    private boolean isExpired;

    @Column(name = "is_credentials_expired")
    private boolean isCredentialsExpired;

    protected AccountState() {
        this.isLocked = false;
        this.isEnabled = false;
        this.isExpired = false;
        this.isCredentialsExpired = false;
    }

    public static AccountState active() {
        return new AccountState(false, true, false, false);
    }

    public void lock() {
        this.isLocked = true;
    }

    public void disable() {
        this.isEnabled = false;
    }

    public void enable() {
        this.isEnabled = true;
    }

    public boolean isNonLocked() {
        return !isLocked;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public boolean isNonExpired() {
        return !isExpired;
    }

    public boolean isCredentialsNonExpired() {
        return !isCredentialsExpired;
    }
}
