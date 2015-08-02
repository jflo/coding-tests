package com.shapeways.recruiting;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArtistPairings {
	
	public static void main(String[] args) {
		try {
			//Set<UnorderedPair<String,String>> uniquePairs = new HashSet<UnorderedPair<String,String>>();
			List<UnorderedPair<String, String>> answers =
			Files.lines(new File("src/main/resources/Artist_lists_small.txt").toPath())
				 .map(s -> Arrays.asList(s.split(",")))
				 .flatMap(l -> allPairs(l).stream())
				 .distinct()
				 .filter(p->countInFile(p) >= 50)
				 .collect(Collectors.toList());
				 
				 
			System.out.println(answers); 
			//System.out.println(uniquePairs.size() + " band permutations found");
			//System.out.println(uniquePairs.toString());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static long countInFile(UnorderedPair<String, String> p)  {
		long retval = 0;
		try(Stream<String> stream = Files.lines(new File("src/main/resources/Artist_lists_small.txt").toPath())) {
			//File content = new File();
			
			retval = stream
				 .map(s -> Arrays.asList(s.split(",")))
				 .filter(l -> l.contains(p.getLeft()) && l.contains(p.getRight()))
				 .count();
			if(retval>50) {
				System.out.println(p + "should make the list");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retval;
	}

	private static List<UnorderedPair<String,String>> allPairs(List<String> l) {
		List<UnorderedPair<String, String>> retval = new ArrayList<UnorderedPair<String, String>>();
		for(int i = 0; i<l.size(); i++) {
			for(int j = 0; j<l.size(); j++) {
				if(i!=j) {
					retval.add(new UnorderedPair<String, String>(l.get(i), l.get(j)));
				}
			}
		}
		return retval;
	}
}
