<script lang="ts">
	import '../app.css';
	import { auth } from '$lib/auth/store.js';
	import { Navbar } from '$lib/components/index.js';
	import { onMount } from 'svelte';

	let { children } = $props();

	onMount(() => {
		auth.initialize();
	});
</script>

<Navbar />

<main class="min-h-screen bg-base-200">
	{@render children()}
</main>
