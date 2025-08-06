<script lang="ts">
	import { auth } from '$lib/auth/store.js';
	import { requireAuth } from '$lib/auth/guards.js';
	import type { UserDTO } from '$lib/types/session.js';
	import { User, Envelope, Calendar } from 'phosphor-svelte';
	import { onMount } from 'svelte';

	let user = $state<UserDTO | null>(null);
	let isLoading = $state(true);

	onMount(() => {
		const unsubscribe = requireAuth();

		auth.initialize().then(() => {
			const unsubscribeAuth = auth.subscribe((state) => {
				if (!state.isLoading) {
					user = state.user;
					isLoading = false;
				}
			});
		});

		return unsubscribe;
	});
</script>

{#if isLoading}
	<div class="flex min-h-screen items-center justify-center">
		<span class="loading loading-lg loading-spinner"></span>
	</div>
{:else if user}
	<div class="container mx-auto px-4 py-8">
		<div class="mb-8">
			<h1 class="mb-2 text-3xl font-bold">Dashboard</h1>
			<p class="text-base-content/70">Welcome to your secure dashboard</p>
		</div>

		<div class="grid grid-cols-1 gap-8 lg:grid-cols-2">
			<div class="card bg-base-100 shadow-xl">
				<div class="card-body">
					<h2 class="card-title">User Profile</h2>
					<div class="space-y-4">
						<div class="flex items-center gap-3">
							<User size={20} class="text-primary" />
							<div>
								<p class="text-sm text-base-content/70">Name</p>
								<p class="font-medium">{user.name}</p>
							</div>
						</div>
						<div class="flex items-center gap-3">
							<Envelope size={20} class="text-primary" />
							<div>
								<p class="text-sm text-base-content/70">Email</p>
								<p class="font-medium">{user.email}</p>
							</div>
						</div>
						<div class="flex items-center gap-3">
							<Calendar size={20} class="text-primary" />
							<div>
								<p class="text-sm text-base-content/70">User ID</p>
								<p class="font-medium">{user.id}</p>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="card bg-base-100 shadow-xl">
				<div class="card-body">
					<h2 class="card-title">Session Info</h2>
					<div class="rounded-lg bg-base-300 p-4">
						<pre class="overflow-auto text-sm">{JSON.stringify({ user }, null, 2)}</pre>
					</div>
				</div>
			</div>
		</div>
	</div>
{/if}
