package com.review.tfg.dto.auth.response;

public class TokenDTO {
	
	private String token;
	
	public TokenDTO() {}

	public TokenDTO(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

    public static JwtAuthenticationResponseBuilder builder() {
        return new JwtAuthenticationResponseBuilder();
    }

    public static class JwtAuthenticationResponseBuilder {
        private String token;

        public JwtAuthenticationResponseBuilder token(String token) {
            this.token = token;
            return this;
        }

        public TokenDTO build() {
            return new TokenDTO(token);
        }
    }
}
