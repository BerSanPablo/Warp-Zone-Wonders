export interface UserData {
  nick:string;
	comunidad:string;
	token:string;
	imagenPerfil:File|null;
	roles:Set<string>;
}
