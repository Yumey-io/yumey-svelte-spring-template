import { json } from '@sveltejs/kit';
import { getApiUrl } from '$lib/config/api.js';

export async function GET({ fetch, request }) {
	try {
		const response = await fetch(`${getApiUrl()}/session`, {
			credentials: 'include',
			headers: {
				cookie: request.headers.get('cookie') || ''
			}
		});

		if (response.ok) {
			const data = await response.json();
			return json(data);
		} else {
			return json({ user: null, isAuthenticated: false });
		}
	} catch (error) {
		console.error('Session check error:', error);
		return json({ user: null, isAuthenticated: false });
	}
}
