import { browser } from '$app/environment';
import { env } from '$env/dynamic/public';

export function getApiUrl(): string {
  if (browser) {
    return env.PUBLIC_API_URL || 'http://localhost:8080';
  }
  return env.PUBLIC_API_URL || 'http://localhost:8080';
}
