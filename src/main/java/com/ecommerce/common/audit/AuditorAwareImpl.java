package com.ecommerce.common.audit;

import com.ecommerce.common.util.AppConstants;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;


public class AuditorAwareImpl implements AuditorAware<String> {

    /**
     * Gets current user for auditing
     */
    @Override
    public Optional<String> getCurrentAuditor() {

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null) {
                return Optional.of(AppConstants.SYSTEM);
            }

            if (!authentication.isAuthenticated()) {
                return Optional.of(AppConstants.SYSTEM);
            }

            if (AppConstants.ANONYMOUS_USER.equals(authentication.getName())) {
                return Optional.of(AppConstants.SYSTEM);
            }

            return Optional.of(authentication.getName());

        } catch (Exception e) {
            return Optional.of(AppConstants.SYSTEM);
        }
    }
}
