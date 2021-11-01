package pe.edu.galaxy.enunmeraciones;

public enum Departamentos {
	LIMA ("01"),
	AMAZONAS("041"),
	ANCASH ("043"),
	APURIMAC ("083"),
	AREQUIPA ("054"),
	AYACUCHO ("066"),
	CAJAMARCA ("076"),
	CUSCO ("084"),
	HUANCAVELICA ("067"),
	HUANUCO ("062"),
	ICA ("056"),
	JUNIN ("064"),
	LALIBERTAD ("044"),
	LAMBAYEQUE ("074"),
	LORETO ("065"),
	MADREDEDIOS ("082"),
	MAQUEGUA ("053"),
	PASCO ("063"),
	PIURA ("073"),
	PUNO ("051"),
	SANMARTIN ("042"),
	TACNA ("052"),
	TUMBES ("072"),
	UCAYALI ("061");
	
	private final String codigo;
	
	Departamentos(String codigo){
		this.codigo = codigo;
	}
	
	
	public String getCodigo() {
		return this.codigo;
	}
}
