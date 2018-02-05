import java.util.*;
import java.io.*;
import java.text.*;

class Game
{
	public static void main(String[] args) throws Exception
	{
		Game m = new Game();
		m.go();
	}

	void go() throws Exception
	{
		// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// Scanner s = new Scanner(System.in);
		// DecimalFormat df = new DecimalFormat("#.####");
		// System.out.printf("%s %.4f\n", name, ratio);
		
		BufferedReader peopleReader = new BufferedReader(new FileReader("people.txt"));
		BufferedReader wordReader = new BufferedReader(new FileReader("words.txt"));

		int P = 9;
		String[] people = new String[P];
		for(int i=0; i<P; i++)
		{
			people[i] = peopleReader.readLine();
		}

		peopleReader.close();

		List<String> words = new ArrayList<String>();
		String line;
		while((line = wordReader.readLine()) != null)
		{
			words.add(line);
		}

		wordReader.close();

		// Randomize people array.
		Collections.shuffle(Arrays.asList(people));
		Collections.shuffle(words);

		// for(int i=0; i<words.size(); i++)
		// {
		// 	System.out.println(words.get(i));
		// }

		Map<String, Target> map = new HashMap<String, Target>();

		for(int i=0; i<=P-1; i++)
		{
			int j = (i+1) % P; // j is the target person
			
			String targetWord = words.get(j);

			Target target = new Target(people[j], targetWord);
			map.put(people[i].toLowerCase(), target);
		}

		Target davidsTarget = new Target("Alex", "handle");
		map.put("david", davidsTarget);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while(true)
		{
			System.out.print("Input your name: ");

			String name = br.readLine();
			name = name.toLowerCase();

			if(!map.containsKey(name))
			{
				System.out.println("This name does not exist in the list.");
				System.out.println("Press enter to clear the screen and try again.");
				br.readLine();
			}
			else
			{
				Target target = map.get(name);
				System.out.println("Your target is " + target.person + " and the word is '" + target.word + "'");
				System.out.println("Press enter to clear the screen and try again.");
				br.readLine();
			}
			// System.out.print("\033[H\033[2J");
			// System.out.flush();
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			// Runtime.getRuntime().exec("cls");
		}

	}

	public class Target
	{
		public String person;
		public String word;

		public Target(String person, String word)
		{
			this.person = person;
			this.word = word;
		}
	}
}