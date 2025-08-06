import { json } from '@sveltejs/kit';
import { serverConfig } from '$lib/config/server.js';

export interface UserDTO {
	id: string;
	email: string;
	name: string;
	avatar?: string;
}

export async function POST({ request }) {
	const { code } = await request.json();

	console.log('API callback received code:', code ? code.substring(0, 10) + '...' : 'undefined');

	if (!code) {
		console.error('No authorization code provided to API');
		return json({ error: 'No authorization code provided' }, { status: 400 });
	}

	try {
		console.log('Exchanging code for token...');
		const tokenResponse = await fetch(serverConfig.oauth.yumey.tokenUrl, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded'
			},
			body: new URLSearchParams({
				client_id: serverConfig.oauth.yumey.clientId,
				client_secret: serverConfig.oauth.yumey.clientSecret,
				code,
				grant_type: 'authorization_code',
				redirect_uri: serverConfig.oauth.yumey.redirectUri
			})
		});

		console.log('Token response status:', tokenResponse.status);

		if (!tokenResponse.ok) {
			const errorText = await tokenResponse.text();
			console.error('Token exchange failed:', errorText);
			throw new Error(`Token exchange failed: ${tokenResponse.statusText} - ${errorText}`);
		}

		const token = await tokenResponse.json();
		console.log('Token received, fetching profile...');

		const profileResponse = await fetch(serverConfig.oauth.yumey.profileUrl, {
			headers: {
				Authorization: `Bearer ${token.access_token}`
			}
		});

		console.log('Profile response status:', profileResponse.status);

		if (!profileResponse.ok) {
			const errorText = await profileResponse.text();
			console.error('Profile fetch failed:', errorText);
			throw new Error(`Profile fetch failed: ${profileResponse.statusText} - ${errorText}`);
		}

		const userProfile = await profileResponse.json();
		console.log('Profile received:', userProfile);

		const userDTO: UserDTO = {
			id: userProfile._id,
			email: userProfile.email,
			name: userProfile.username,
			avatar: userProfile.avatar
		};

		console.log('Returning user DTO:', userDTO);

		return json({
			user: userDTO,
			accessToken: token.access_token,
			expiresIn: token.expires_in,
			expiresAt: Date.now() + token.expires_in * 1000
		});
	} catch (error) {
		console.error('OAuth callback error:', error);
		return json({ error: 'Authentication failed' }, { status: 500 });
	}
}
