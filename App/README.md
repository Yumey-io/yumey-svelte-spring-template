# Yumey OAuth Template

<div align="center">
  <img src="static/favicon.svg" alt="Yumey" width="64" height="64" />
  <h1>Secure OAuth 2.0 Authentication Template</h1>
  <p>A modern, modular authentication system built with SvelteKit 5.0 and OAuth 2.0</p>
  <p>You can find a hosted version of this repository on https://yumey-svelte-template.vercel.app/</p>
</div>

## ✨ Features

- 🔐 **OAuth 2.0 Integration** - Secure authentication with Yumey provider
- 🍪 **HTTP-Only Cookies** - Server-side session management
- 🏗️ **Modular Architecture** - Clean, scalable code structure
- 🎨 **Modern UI** - Beautiful DaisyUI components with Tailwind CSS
- ⚡ **Svelte 5 Runes** - Latest reactive state management
- 🔒 **Type Safety** - Full TypeScript support throughout
- 📱 **Responsive Design** - Works perfectly on all devices

## 🚀 Quick Start

### Prerequisites

- [Bun](https://bun.sh/) (latest version)
- Node.js 18+ (for compatibility)

### Installation

1. **Clone the repository**

   ```bash
   git clone https://github.com/Yumey-io/yumey-svelte-template.git
   cd yumey-svelte-template
   ```

2. **Install dependencies**

   ```bash
   bun install
   ```

3. **Set up environment variables**

   ```bash
   cp example.env .env
   ```

4. **Configure OAuth credentials**

   ```env
   YUMEY_CLIENT_ID=your_yumey_client_id_here
   YUMEY_CLIENT_SECRET=your_yumey_client_secret_here
   YUMEY_REDIRECT_URI=http://localhost:5173/auth/callback
   YUMEY_AUTH_URL=https://yumey-auth-server.vercel.app/oauth2/authorize
   YUMEY_TOKEN_URL=https://yumey-auth-server.vercel.app/oauth2/token
   YUMEY_PROFILE_URL=https://yumey-auth-server.vercel.app/api/user/profile
   SESSION_SECRET=your_session_secret_here
   ```

5. **Start development server**

   ```bash
   bun run dev
   ```

6. **Open your browser**
   ```
   http://localhost:5173
   ```

## 📁 Project Structure

```
src/
├── lib/
│   ├── auth/           # Authentication module
│   │   ├── store.ts    # Auth state management
│   │   ├── guards.ts   # Route protection
│   │   └── index.ts    # Module exports
│   ├── config/         # Configuration module
│   │   ├── routes.ts   # Route definitions
│   │   ├── server.ts   # Server-side config
│   │   └── index.ts    # Module exports
│   ├── types/          # Type definitions
│   │   ├── session.ts  # Session types
│   │   └── index.ts    # Type exports
│   ├── components/     # Reusable components
│   │   ├── Navbar.svelte
│   │   └── index.ts    # Component exports
│   └── index.ts        # Main lib exports
├── routes/             # SvelteKit routes
│   ├── api/            # API endpoints
│   ├── auth/           # OAuth callbacks
│   ├── dashboard/      # Protected dashboard
│   └── login/          # Login page
└── app.html            # HTML template
```

## 🔧 Available Scripts

```bash
# Development
bun run dev          # Start development server
bun run dev --open   # Start dev server and open browser

# Building
bun run build        # Build for production
bun run preview      # Preview production build

# Code Quality
bun run check        # Type checking
bun run check:watch  # Watch mode type checking
bun run format       # Format code with Prettier
bun run lint         # Lint code with ESLint
```

## 🛣️ Routes

| Route            | Description                      | Auth Required |
| ---------------- | -------------------------------- | ------------- |
| `/`              | Home page with feature showcase  | ❌            |
| `/login`         | OAuth login page                 | ❌            |
| `/dashboard`     | User dashboard with profile data | ✅            |
| `/auth/callback` | OAuth callback handler           | ❌            |

## 🔐 Authentication Flow

1. **User visits protected route** → Redirected to `/login`
2. **Clicks "Sign in with Yumey"** → Redirected to OAuth provider
3. **Authorizes application** → Redirected back with code
4. **Server exchanges code** → Gets access token and user profile
5. **Session created** → HTTP-only cookie set
6. **Redirected to dashboard** → User sees their profile data

## 🏗️ Architecture

### Server-Side Security

- All OAuth operations happen server-side
- Sensitive credentials never exposed to client
- HTTP-only cookies for session management
- Automatic session validation and cleanup

### Modular Design

- **Auth Module** - Authentication state and guards
- **Config Module** - Route and server configuration
- **Types Module** - TypeScript type definitions
- **Components Module** - Reusable UI components

### Tech Stack

- **SvelteKit 5.0** - Full-stack framework
- **TypeScript** - Type safety
- **DaisyUI** - Component library
- **Tailwind CSS** - Utility-first styling
- **Phosphor Icons** - Icon library
- **Bun** - Fast JavaScript runtime

## 🎨 Customization

### Adding New Routes

1. Create route in `src/routes/`
2. Update route protection in `src/lib/config/routes.ts`
3. Add navigation in `src/lib/components/Navbar.svelte`

### Adding New OAuth Providers

1. Update server config in `src/lib/config/server.ts`
2. Modify OAuth service in API endpoints
3. Update user profile mapping

### Styling

- Modify `src/app.css` for global styles
- Use DaisyUI classes for components
- Customize theme in `src/app.css`

## 🚀 Deployment

### Vercel (Recommended)

```bash
# Install Vercel CLI
bun add -g vercel

# Deploy
vercel
```

### Other Platforms

- **Netlify** - Use `@sveltejs/adapter-netlify`
- **Railway** - Use `@sveltejs/adapter-node`
- **Docker** - Use `@sveltejs/adapter-node`

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- [SvelteKit](https://kit.svelte.dev/) - The web framework
- [DaisyUI](https://daisyui.com/) - Component library
- [Tailwind CSS](https://tailwindcss.com/) - CSS framework
- [Phosphor Icons](https://phosphoricons.com/) - Icon library
