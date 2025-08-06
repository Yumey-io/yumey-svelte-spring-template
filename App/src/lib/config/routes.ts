export const config = {
	routes: {
		public: ['/login', '/auth/callback'],
		protected: ['/dashboard', '/profile', '/settings']
	}
} as const;

export function isProtectedRoute(pathname: string): boolean {
	return config.routes.protected.some((route) => pathname.startsWith(route));
}

export function isPublicRoute(pathname: string): boolean {
	return config.routes.public.some((route) => pathname.startsWith(route));
}
