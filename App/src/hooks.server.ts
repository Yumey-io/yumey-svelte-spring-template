import { redirect } from '@sveltejs/kit';
import { isProtectedRoute } from '$lib/config/routes.js';
import type { Handle } from '@sveltejs/kit';

export const handle: Handle = async ({ event, resolve }) => {
	const { pathname } = event.url;

	if (isProtectedRoute(pathname)) {
		const session = event.cookies.get('session');
		if (!session) {
			throw redirect(302, '/login');
		}
	}

	return resolve(event);
};
