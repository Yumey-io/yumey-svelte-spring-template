import { writable } from 'svelte/store';
import type { UserDTO } from '../types/session.js';

export interface AuthState {
	user: UserDTO | null;
	isAuthenticated: boolean;
	isLoading: boolean;
}

function createAuthStore() {
	const { subscribe, set, update } = writable<AuthState>({
		user: null,
		isAuthenticated: false,
		isLoading: true
	});

	async function initialize() {
		try {
			const response = await fetch('/api/auth/session');
			const data = await response.json();

			set({
				user: data.user,
				isAuthenticated: data.isAuthenticated,
				isLoading: false
			});
		} catch (error) {
			console.error('Failed to initialize auth:', error);
			set({
				user: null,
				isAuthenticated: false,
				isLoading: false
			});
		}
	}

	async function logout() {
		try {
			await fetch('/api/auth/session', { method: 'DELETE' });
		} catch (error) {
			console.error('Failed to logout:', error);
		}

		set({
			user: null,
			isAuthenticated: false,
			isLoading: false
		});
	}

	function setLoading(loading: boolean) {
		update((state) => ({ ...state, isLoading: loading }));
	}

	return {
		subscribe,
		initialize,
		logout,
		setLoading
	};
}

export const auth = createAuthStore();
