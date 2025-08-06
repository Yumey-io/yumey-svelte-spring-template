import { json } from '@sveltejs/kit';

export async function GET({ cookies }) {
	const sessionCookie = cookies.get('session');

	if (!sessionCookie) {
		return json({ user: null, isAuthenticated: false });
	}

	try {
		const session = JSON.parse(sessionCookie);
		const now = Date.now();

		if (session.expiresAt && now < session.expiresAt) {
			return json({
				user: session.user,
				isAuthenticated: true
			});
		} else {
			cookies.delete('session', { path: '/' });
			return json({ user: null, isAuthenticated: false });
		}
	} catch (error) {
		console.error('Session parsing error:', error);
		cookies.delete('session', { path: '/' });
		return json({ user: null, isAuthenticated: false });
	}
}

export async function DELETE({ cookies }) {
	cookies.delete('session', { path: '/' });
	return json({ success: true });
}
