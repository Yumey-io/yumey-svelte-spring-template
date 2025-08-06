<script lang="ts">
	import { getApiUrl } from '$lib/config/api.js';

	function handleLogin() {
		window.location.href = `${getApiUrl()}/oauth2/authorization/yumey`;
	}
</script>

<div class="flex min-h-screen items-center justify-center">
	<div class="card w-96 bg-base-100 shadow-xl">
		<div class="card-body">
			<div class="mb-6 text-center">
				<img src="/favicon.svg" alt="Yumey" class="mx-auto mb-4 h-16 w-16" />
				<h2 class="text-2xl font-bold">Welcome Back</h2>
				<p class="text-base-content/70">Sign in to access your account</p>
			</div>

			<button class="btn w-full btn-primary" on:click={handleLogin}>
				<img src="/favicon.svg" alt="Yumey" class="h-5 w-5" />
				Sign in with Yumey
			</button>

			<div class="divider">or</div>

			<div class="text-center text-sm text-base-content/70">
				<p>By signing in, you agree to our terms of service and privacy policy.</p>
			</div>
		</div>
	</div>
</div>
