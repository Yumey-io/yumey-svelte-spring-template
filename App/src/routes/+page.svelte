<script lang="ts">
	import { auth } from '$lib/auth/store.js';
	import type { UserDTO } from '$lib/types/session.js';
	import { Shield, Users, Lightning, ArrowRight, CheckCircle } from 'phosphor-svelte';

	let isAuthenticated = $state(false);
	let user = $state<UserDTO | null>(null);

	auth.subscribe((state) => {
		isAuthenticated = state.isAuthenticated;
		user = state.user;
	});
</script>

<div class="hero min-h-screen">
	<div class="hero-content text-center text-neutral-content">
		<div class="max-w-md">
			<h1 class="mb-5 text-5xl font-bold">Welcome to Yumey</h1>
			<p class="mb-5">
				A secure, modern authentication system built with SvelteKit 5.0 and OAuth 2.0. Experience
				seamless login with enterprise-grade security.
			</p>
			{#if isAuthenticated && user}
				<div class="card bg-base-100 text-base-content shadow-xl">
					<div class="card-body">
						<h2 class="card-title">Welcome back, {user.name}!</h2>
						<p>You're successfully authenticated with Yumey OAuth.</p>
						<div class="card-actions justify-end">
							<a href="/dashboard" class="btn btn-primary">
								Go to Dashboard
								<ArrowRight size={16} />
							</a>
						</div>
					</div>
				</div>
			{:else}
				<a href="/login" class="btn btn-primary">
					Get Started
					<ArrowRight size={16} />
				</a>
			{/if}
		</div>
	</div>
</div>

<div class="container mx-auto px-4 py-16">
	<div class="mb-12 text-center">
		<h2 class="mb-4 text-3xl font-bold">Why Choose Yumey?</h2>
		<p class="text-lg text-base-content/70">Built for modern web applications</p>
	</div>

	<div class="mb-16 grid grid-cols-1 gap-8 md:grid-cols-3">
		<div class="card bg-base-100 shadow-xl">
			<div class="card-body text-center">
				<Shield size={48} class="mx-auto mb-4 text-primary" />
				<h3 class="card-title justify-center">Secure Authentication</h3>
				<p>OAuth 2.0 with enterprise-grade security and HTTP-only cookies.</p>
			</div>
		</div>

		<div class="card bg-base-100 shadow-xl">
			<div class="card-body text-center">
				<Users size={48} class="mx-auto mb-4 text-primary" />
				<h3 class="card-title justify-center">User Management</h3>
				<p>Complete user profile management with session persistence.</p>
			</div>
		</div>

		<div class="card bg-base-100 shadow-xl">
			<div class="card-body text-center">
				<Lightning size={48} class="mx-auto mb-4 text-primary" />
				<h3 class="card-title justify-center">Modern Tech Stack</h3>
				<p>Built with SvelteKit 5.0, TypeScript, and DaisyUI.</p>
			</div>
		</div>
	</div>

	<div class="rounded-lg bg-base-100 p-8 shadow-xl">
		<h3 class="mb-6 text-center text-2xl font-bold">Features</h3>
		<div class="grid grid-cols-1 gap-6 md:grid-cols-2">
			<div class="flex items-start gap-3">
				<CheckCircle size={24} class="mt-1 text-success" />
				<div>
					<h4 class="font-semibold">OAuth 2.0 Integration</h4>
					<p class="text-sm text-base-content/70">Secure authentication with Yumey provider</p>
				</div>
			</div>
			<div class="flex items-start gap-3">
				<CheckCircle size={24} class="mt-1 text-success" />
				<div>
					<h4 class="font-semibold">HTTP-Only Cookies</h4>
					<p class="text-sm text-base-content/70">Secure session management</p>
				</div>
			</div>
			<div class="flex items-start gap-3">
				<CheckCircle size={24} class="mt-1 text-success" />
				<div>
					<h4 class="font-semibold">TypeScript Support</h4>
					<p class="text-sm text-base-content/70">Full type safety throughout</p>
				</div>
			</div>
			<div class="flex items-start gap-3">
				<CheckCircle size={24} class="mt-1 text-success" />
				<div>
					<h4 class="font-semibold">Svelte 5 Runes</h4>
					<p class="text-sm text-base-content/70">Modern reactive state management</p>
				</div>
			</div>
			<div class="flex items-start gap-3">
				<CheckCircle size={24} class="mt-1 text-success" />
				<div>
					<h4 class="font-semibold">Modular Architecture</h4>
					<p class="text-sm text-base-content/70">Clean, scalable code structure</p>
				</div>
			</div>
			<div class="flex items-start gap-3">
				<CheckCircle size={24} class="mt-1 text-success" />
				<div>
					<h4 class="font-semibold">DaisyUI Components</h4>
					<p class="text-sm text-base-content/70">Beautiful, responsive design</p>
				</div>
			</div>
		</div>
	</div>
</div>
