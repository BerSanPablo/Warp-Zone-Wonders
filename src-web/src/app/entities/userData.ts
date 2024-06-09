export interface UserData {
  nick:string;
  email:string;
	comunidad:string;
	token:string;
	imagenPerfil:File|null;
	roles:Set<string>;
}
