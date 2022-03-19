
public class JeuGuerrier {
	
	public static void main(String[] args) {
		EquipeGuerriers equipe = new EquipeGuerriers(3, 10);
		int pointsDeVieDuMal = 30;

		while (equipe.nombreGuerriersEnVie() > 0 && pointsDeVieDuMal > 0){
			System.out.println("L'équipe compte " + equipe.nombreGuerriersEnVie() + " guerriers en vie");
			System.out.println(equipe);
			int lancerDe = lancerDe();
			Guerrier lastWarrior = equipe.jouer(lancerDe);
			System.out.println("Suite au combat entre la creature du mal et le guerrier n°" + lastWarrior.getNumero());
			System.out.println("Le guerrier vient de perdre " + lancerDe + " points de vie");
			if (lastWarrior.getPointsDeVie() <= 0){
				System.out.println("Le guerrier est mort");
			} else System.out.println("Il lui reste " + lastWarrior.getPointsDeVie() + " points de vie");
			lancerDe = lancerDe();
			pointsDeVieDuMal -= lancerDe;
			System.out.println("La creature du mal vient de perdre " + lancerDe + " points de vie");
			System.out.println("Il lui reste " + pointsDeVieDuMal + " points de vie");
			if (pointsDeVieDuMal <= 0){
				System.out.println("La créature du mal est morte");
			}
		}

		if (equipe.nombreGuerriersEnVie() > 0){
			System.out.println("Les guerriers ont gagné");
		} else System.out.println("La créature du mal a gagné");


	}
	
	public static int lancerDe (){
		double nombreReel;
		nombreReel = Math.random();
		return (int) (nombreReel * 6) + 1;
	}
	
}
