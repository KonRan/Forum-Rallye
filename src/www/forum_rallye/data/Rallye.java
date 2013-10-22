package www.forum_rallye.data;

import java.util.GregorianCalendar;

public class Rallye {

		private String nom;
		private GregorianCalendar dateDeb;
		private GregorianCalendar dateFin;
		private long id;
		
		public Rallye()
		{
			id = -1;
			nom = null;
			dateDeb = null;
			dateFin = null;
		}

		public String getNom() {
			return nom;
		}
		public void setNom(String nom) {
			this.nom = nom;
		}
		
		public GregorianCalendar getDateDeb() {
			return dateDeb;
		}
		
		public void setDateDeb(GregorianCalendar dateDeb) {
			this.dateDeb = dateDeb;
		}
		
		public GregorianCalendar getDateFin() {
			return dateFin;
		}
		
		public void setDateFin(GregorianCalendar dateFin) {
			this.dateFin = dateFin;
		}
		
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
	}


