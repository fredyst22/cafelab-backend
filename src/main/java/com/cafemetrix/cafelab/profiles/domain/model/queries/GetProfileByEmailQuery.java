package com.cafemetrix.cafelab.profiles.domain.model.queries;

import com.cafemetrix.cafelab.profiles.domain.model.valueobjects.EmailAddress;

/**
 * Get Profile By Email Query
 */
public record GetProfileByEmailQuery(EmailAddress emailAddress) {
}
