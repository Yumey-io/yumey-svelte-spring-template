<script lang="ts">
	import type { ExampleDTO } from '$lib/types/example.js';
	import { Calendar, User } from 'phosphor-svelte';
	import { getApiUrl } from '$lib/config/api.js';

	let exampleData = $state<ExampleDTO | null>(null);
	let isLoading = $state(true);
	let error = $state<string | null>(null);

	async function fetchExampleData() {
		isLoading = true;
		error = null;

		try {
			const response = await fetch(`${getApiUrl()}/example`, {
				credentials: 'include'
			});

			if (!response.ok) {
				if (response.status === 401 || response.status === 403) {
					window.location.href = `${getApiUrl()}/oauth2/authorization/yumey`;
					return;
				}
				throw new Error(`Failed to fetch example data: ${response.statusText}`);
			}

			exampleData = await response.json();
		} catch (err) {
			error = err instanceof Error ? err.message : 'An unknown error occurred';
		} finally {
			isLoading = false;
		}
	}

	$effect(() => {
		fetchExampleData();
	});
</script>

<div class="card bg-base-100 shadow-xl">
	<div class="card-body">
		<h2 class="card-title">Example Data from Backend</h2>

		{#if isLoading}
			<div class="flex justify-center py-4">
				<span class="loading loading-md loading-spinner"></span>
			</div>
		{:else if error}
			<div class="alert alert-error">
				<p>{error}</p>
				<button class="btn btn-sm" on:click={fetchExampleData}>Retry</button>
			</div>
		{:else if exampleData}
			<div class="space-y-4">
				<div class="flex items-center gap-3">
					<User size={20} class="text-primary" />
					<div>
						<p class="text-sm text-base-content/70">Name</p>
						<p class="font-medium">{exampleData.name}</p>
					</div>
				</div>
				<div class="flex items-center gap-3">
					<Calendar size={20} class="text-primary" />
					<div>
						<p class="text-sm text-base-content/70">Age in Days</p>
						<p class="font-medium">{exampleData.ageInDays}</p>
					</div>
				</div>
			</div>

			<div class="mt-4 rounded-lg bg-base-300 p-4">
				<pre class="overflow-auto text-sm">{JSON.stringify(exampleData, null, 2)}</pre>
			</div>
		{:else}
			<div class="alert alert-warning">
				<p>No example data available</p>
			</div>
		{/if}
	</div>
</div>
