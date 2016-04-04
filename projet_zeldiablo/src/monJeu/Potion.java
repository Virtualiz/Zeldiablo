package monJeu;

public class Potion implements Objet{

	@Override
	public String getNomObjet() {
		return "Potion";
	}

	@Override
	public boolean etreUtilise(Heros h) {
		h.subirDegat(-5);
		if (h.getPv() > Heros.PV_MAX)
		{
			h.subirDegat(h.getPv()-Heros.PV_MAX);
		}
		return true;
	}

	@Override
	public Object getType() {
		return "Potion";
	}

}
