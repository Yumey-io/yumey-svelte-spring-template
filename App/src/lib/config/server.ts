import { env } from '$env/dynamic/private';

export const serverConfig = {
	oauth: {
		yumey: {
			clientId: env.YUMEY_CLIENT_ID,
			clientSecret: env.YUMEY_CLIENT_SECRET,
			redirectUri: env.YUMEY_REDIRECT_URI,
			authUrl: env.YUMEY_AUTH_URL,
			tokenUrl: env.YUMEY_TOKEN_URL,
			profileUrl: env.YUMEY_PROFILE_URL
		}
	},
	session: {
		secret: env.SESSION_SECRET
	}
} as const;
