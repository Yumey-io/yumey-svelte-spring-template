import { json } from '@sveltejs/kit';
import { dev } from '$app/environment';

export async function GET({ url, fetch, cookies }) {
	const code = url.searchParams.get('code');
	const error = url.searchParams.get('error');

	if (error) {
		console.error('OAuth error:', error);
		return json({ error: 'Authorization failed' }, { status: 400 });
	}

	if (!code) {
		console.error('No authorization code provided');
		return json({ error: 'No authorization code provided' }, { status: 400 });
	}

	try {
		const response = await fetch('/api/auth/callback', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify({ code })
		});

		if (!response.ok) {
			const errorText = await response.text();
			console.error('API call failed:', errorText);
			throw new Error(`Authentication failed: ${response.status} ${errorText}`);
		}

		const data = await response.json();

		cookies.set('session', JSON.stringify(data), {
			path: '/',
			httpOnly: true,
			secure: !dev,
			sameSite: 'lax',
			maxAge: data.expiresIn
		});

		return new Response(null, {
			status: 302,
			headers: {
				Location: '/dashboard'
			}
		});
	} catch (error) {
		console.error('OAuth callback error:', error);
		return json({ error: 'Authentication failed' }, { status: 500 });
	}
}
