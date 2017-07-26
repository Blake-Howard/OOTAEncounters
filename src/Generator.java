import java.util.Scanner;
import java.util.Random;

public class Generator {
	
	public static void main(String[] args) {
		boolean menu = true;
		Scanner sc = new Scanner(System.in);
		Generator g = new Generator();
		while (menu) {
			System.out.println("Type 1 to generate, 0 to quit");
			int selection = sc.nextInt();
			if( selection == 1) { System.out.println(g.generate());}
			else if( selection == 2) {
				System.out.println("Generate for how many days of Travel?");
				int days = sc.nextInt();
				for(; days > 0; days--) {
					System.out.println(g.generate());
				}
			}
			else menu = false;
		}
		sc.close();
		
	}	
	
	public String generate() {
		int event = roll(1,20);
		String result = "";
		
		//Battle aftermath
		if(event == 1 || event == 2) {
			int corpse = roll(1,10);
			int scavengers = roll(1,6);
			String str1;
			String str2;
			
			//Corpse table
			if (corpse == 1) {str1 = "1 dead behir";}
			else if(corpse == 2 || corpse ==3) {str1 = roll(1,4) + " dead Drow and " + roll(1,4,-1) + " dead giant lizards";}
			else if(corpse == 4 || corpse ==5) {str1 = roll(3,8) + " dead giant fire beetles (their glands no longer glowing)";}
			else if(corpse == 6 || corpse == 7) {str1 = roll(2,4) + " dead gricks"; }
			else if(corpse == 8 || corpse == 9) {str1 = roll(2,4) + " dead kuo-toa";}
			else if(corpse == 10) {str1 = "1 dead purple worm";}
			else str1 = "Error with corpses";
			
			//Scavenger table
			if (scavengers == 1 || scavengers == 2) {str2 = roll(1,3) + " black puddings";}
			else if(scavengers == 3 || scavengers == 4) {str2 = roll(1,4) + " carrion crawlers";}
			else if(scavengers == 5) {str2 = roll(1,6) + " gnolls " + roll(1,6) + " hyenas";}
			else if(scavengers == 6) {str2 = "1 otyugh";}
			else str2 = "Error with scavengers";
			
			result = str1 + " being eaten by " + str2;
			return result;
		}
		//Creature encounter
		else if(event >= 3 && event <=6) {
			int die = roll(1,6);
			
			if (die < 5) { result = "Hard creature encounter";}
			else result = "Deadly creature encounter";
			
			return result;
		}
		//Demon encounter
		else if(event >= 7 && event <= 9) {
			int die = roll(1,20);
			
			if(die >= 1 && die <=4) { result = roll(1,4) + " balguras";}
			else if(die >= 5 && die <= 8) { result = roll(1,4) + " chasmes";}
			else if(die == 9 || die == 10) {result = roll(1,2) + " hezrous";}
			else if(die >=11 || die <= 14) {result = roll(1,4) + " Shadow Demons";}
			else if(die >=15 && die <=18) {result = roll(1,3) + "vrocks";}
			else if(die == 19 || die == 20) {result = "Here comes Juiblex";}
			else result = "Error in Demon encounter";
		}
		//Discipline Problem
		else if(event == 10 || event == 11) {
			int die = roll(1,3);
			
			switch (die) {
			case 1: int monsters = roll(1,100);
					String str1;
					if(monsters < 51) str1 = " doesn't attract ";
					else str1 = " does attract ";
					result = "A loud argument that" + str1 + "monsters";
					return result;
			case 2: result = "Theft or dispute over the division of spoils";
					return result;
			case 3: result = "Brawl or other outbreak of violence";
					return result;
			default: return "Error in Discipline Problem";
			}
		}
		//Disease
		else if(event == 12 || event == 13) {
			int men = roll(1,4);
			int die = roll(1,6);
			String disease;
			
			if (die == 1 || die == 2) disease = "Cackle Fever";
			else if(die == 3 || die == 4) disease = "Sewer Plague";
			else if(die == 5) disease = "Sight Rot";
			else if(die == 6) disease = "Zuggtmoy's spores";
			else return "Error in Disease";
			
			result = men + " NPC members affected by " + disease;
		}
		//Madness
		else if(event == 14 || event == 15) {
			int men = roll(1,6);
			int die = roll(1,6);
			String madness;
			
			if (die >= 1 && die <=3) madness = "Short-term madness";
			else if(die == 4 || die == 5) madness = "Long-term madness";
			else if(die == 6) madness = "Indefinite madness";
			else return "Error in madness";
			
			result = men + " NPC members affected by " + madness;
		}
		//Poisoned NPCs
		else if(event == 16 || event == 17) {
			int hours = roll(1,10) * 10;
			int men = roll(1,12);
			
			result = men + " men poisoned for " + hours + " hours.";
		}
		//Spoiled supplies ( I changed this to exhaustion)
		else if(event == 18 || event == 19) {
			int men = roll(2,4);
			
			result = men + " men suffer a level of exhaustion";
		}
		//Vanishing NPCs
		else if(event == 20) {
			int die = roll(1,4);
			
			result = die + " men go missing and are never seen again. No trace can be found magically or otherwise.";
		}
		//Error message
		else return "Error in generate()";
		
		return result;
	}
	
	public int roll(int amount, int die, int mod) {
		Random rand = new Random();
		int total = 0;
		
		for (;amount > 0; amount--) {
			total += rand.nextInt(die) + 1 + mod;
		}
		return total;
	}
	public int roll(int amount, int die) {
		Random rand = new Random();
		int total = 0;
		
		for (;amount > 0; amount--) {
			total += rand.nextInt(die) + 1;
		}
		return total;
	}
}
