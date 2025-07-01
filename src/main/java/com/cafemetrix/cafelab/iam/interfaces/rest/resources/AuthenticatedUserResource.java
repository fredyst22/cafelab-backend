package com.cafemetrix.cafelab.iam.interfaces.rest.resources;

public record AuthenticatedUserResource(Long id, String username, String token) {
}
