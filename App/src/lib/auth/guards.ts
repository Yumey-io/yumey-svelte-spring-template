import { auth } from './store.js';
import { isProtectedRoute } from '../config/routes.js';
import { browser } from '$app/environment';
import { goto } from '$app/navigation';

export function createRouteGuard() {
	if (!browser) return;

	const unsubscribe = auth.subscribe((state) => {
		if (!state.isLoading) {
			const pathname = window.location.pathname;

			if (isProtectedRoute(pathname) && !state.isAuthenticated) {
				goto('/login');
			}
		}
	});

	return unsubscribe;
}

export function requireAuth() {
	if (!browser) return;

	const session = auth.subscribe((state) => {
		if (!state.isLoading && !state.isAuthenticated) {
			goto('/login');
		}
	});

	return session;
}
