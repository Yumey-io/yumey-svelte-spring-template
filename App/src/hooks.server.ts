import { redirect } from '@sveltejs/kit';
import { isProtectedRoute } from '$lib/config/routes.js';
import type { Handle } from '@sveltejs/kit';
import { getApiUrl } from '$lib/config/api.js';

export const handle: Handle = async ({ event, resolve }) => {
	const { pathname } = event.url;

	if (isProtectedRoute(pathname)) {
		const response = await fetch(`${getApiUrl()}/session`, {
			headers: {
				cookie: event.request.headers.get('cookie') || ''
			}
		}).catch(() => null);

		if (!response || !response.ok) {
			throw redirect(302, `${getApiUrl()}/oauth2/authorization/yumey`);
		}
	}

	return resolve(event);
};
