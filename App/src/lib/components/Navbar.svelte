<script lang="ts">
	import { auth } from '$lib/auth/store.js';
	import type { UserDTO } from '$lib/types/session.js';
	import { User } from 'phosphor-svelte';

	let user = $state<UserDTO | null>(null);
	let isAuthenticated = $state(false);
	let isLoading = $state(true);

	auth.subscribe((state) => {
		user = state.user;
		isAuthenticated = state.isAuthenticated;
		isLoading = state.isLoading;
	});

	async function handleLogout() {
		await auth.logout();
	}
</script>

<div class="navbar bg-base-100 shadow-lg">
	<div class="navbar-start">
		<div class="flex items-center gap-2">
			<img src="/favicon.svg" alt="Yumey" class="h-8 w-8" />
			<a href="/" class="text-xl font-bold">Yumey App</a>
		</div>
	</div>

	<div class="navbar-center">
		<ul class="menu menu-horizontal px-1">
			<li><a href="/">Home</a></li>
			{#if isAuthenticated}
				<li><a href="/dashboard">Dashboard</a></li>
			{/if}
		</ul>
	</div>

	<div class="navbar-end">
		{#if isLoading}
			<span class="loading loading-sm loading-spinner"></span>
		{:else if isAuthenticated && user}
			<div class="dropdown dropdown-end">
				<div tabindex="0" role="button" class="btn avatar btn-circle btn-ghost">
					<div class="w-10 rounded-full">
						{#if user.avatar}
							<img src={user.avatar} alt={user.name} />
						{:else}
							<div
								class="flex h-10 w-10 items-center justify-center rounded-full bg-primary text-primary-content"
							>
								<User size={20} />
							</div>
						{/if}
					</div>
				</div>
				<ul
					tabindex="0"
					class="dropdown-content menu z-[1] mt-3 w-52 menu-sm rounded-box bg-base-100 p-2 shadow"
				>
					<li><span class="text-sm opacity-70">{user.email}</span></li>
					<li><hr class="my-1" /></li>
					<li><button on:click={handleLogout}>Logout</button></li>
				</ul>
			</div>
		{:else}
			<a href="/login" class="btn btn-primary">Sign In</a>
		{/if}
	</div>
</div>
