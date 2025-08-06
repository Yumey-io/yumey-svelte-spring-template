import { json } from '@sveltejs/kit';
import { serverConfig } from '$lib/config/server.js';

export async function GET() {
	const params = new URLSearchParams({
		client_id: serverConfig.oauth.yumey.clientId,
		redirect_uri: serverConfig.oauth.yumey.redirectUri,
		response_type: 'code',
		scope: 'read:user email profile'
	});

	const authUrl = `${serverConfig.oauth.yumey.authUrl}?${params.toString()}`;

	return json({ authUrl });
}
