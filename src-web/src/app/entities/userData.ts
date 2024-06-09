export interface UserData {
  nick:string;
  email:string;
	comunidad:string;
	token:string;
	imagenPerfil:File|null;
	roles:Array<string>;
}
