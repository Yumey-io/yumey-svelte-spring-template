import { auth } from './store.js';
import { isProtectedRoute } from '../config/routes.js';
import { browser } from '$app/environment';
import { getApiUrl } from '$lib/config/api.js';

export function createRouteGuard() {
	if (!browser) return;

	const unsubscribe = auth.subscribe((state) => {
		if (!state.isLoading) {
			const pathname = window.location.pathname;

			if (isProtectedRoute(pathname) && !state.isAuthenticated) {
				window.location.href = `${getApiUrl()}/oauth2/authorization/yumey`;
			}
		}
	});

	return unsubscribe;
}

export function requireAuth() {
	if (!browser) return;

	const session = auth.subscribe((state) => {
		if (!state.isLoading && !state.isAuthenticated) {
			window.location.href = `${getApiUrl()}/oauth2/authorization/yumey`;
		}
	});

	return session;
}
