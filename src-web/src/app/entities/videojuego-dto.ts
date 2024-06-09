export interface VideojuegoDTO {
  nombre:string;
	fechaCreacion:Date;
	imagenPortada:File|null;
	sinopsis:string;
	tags:Array<string>;
}
