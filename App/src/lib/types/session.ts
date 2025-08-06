export interface UserDTO {
	id: string;
	email: string;
	name: string;
	avatar?: string;
}

export interface Session {
	user: UserDTO;
	accessToken: string;
	expiresIn: number;
	expiresAt: number;
}
