package com.shapeways.recruiting;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArtistPairings {
	
	public static void main(String[] args) {
		
		File sourceFile = new File("src/main/resources/Artist_lists_small.txt");
		File outputFile = new File("target/popular-pairings.txt");
		int threshold = 50; //number of times a pair must occurr
		
		System.out.println("Beginning analysis of file at "+sourceFile.getAbsolutePath());
		
		Set<String> popular = refinePopular(sourceFile, threshold);
		try(Stream<String> stream = Files.lines(sourceFile.toPath()).parallel()) {
			List<Pair<String, String>> answers = stream
				 .map(s -> Arrays.asList(s.split(",")))
				 .flatMap(l -> allPairs(l).parallelStream())
				 .distinct()
				 .filter(p -> popular.contains(p.getLeft()) && popular.contains(p.getRight()))
				 .filter(p->occurrsTimes(p, threshold, sourceFile))
				 .collect(Collectors.toList());
				 
			
			try(FileWriter fos = new FileWriter(outputFile)) {
				if(!outputFile.exists()) {
					outputFile.createNewFile();
				}
				List<Pair<Pair<String, String>, Long>> sorted = answers.stream()
					   .map(p -> new Pair<Pair<String, String>, Long>(p, countInFile(p, sourceFile)))
					   .sorted((v1, v2) -> -Long.compare(v1.getRight(), v2.getRight()))
					   .collect(Collectors.toList());
				
				for(Pair<Pair<String, String>, Long> p : sorted) {
					fos.write(p.getLeft().toString()+","+p.getRight()+ "\n");
				}
				System.out.println("Output can be found in "+outputFile.getAbsolutePath());
			} catch(IOException ioe) {
				ioe.printStackTrace();
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static Set<String> refinePopular(File sourceFile, int threshold) {
		Set<String> popular = new HashSet<String>();
		try(Stream<String> stream = Files.lines(sourceFile.toPath()).parallel()) {
			final Map<String, Long> bandFreq = stream
					.map(s -> Arrays.asList(s.split(",")))
					.flatMap(l -> l.parallelStream())
					.collect(groupingBy(Function.identity(), counting()));
		
			bandFreq.keySet().stream()
					 .filter(k -> bandFreq.get(k) >= threshold)
					 .forEach(n -> popular.add(n));
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		return popular;
	}
	
	private static boolean occurrsTimes(Pair<String, String> p, int threshold, File inFile) {
		boolean retval = false;
		try(Stream<String> stream = Files.lines(inFile.toPath())) {
			//File content = new File();
			
			long count = stream
				 .map(s -> Arrays.asList(s.split(",")))
				 .filter(l -> l.contains(p.getLeft()) && l.contains(p.getRight()))
				 .limit(threshold)
				 .count();
			if(count>=threshold) {
				//System.out.println(p + "should make the list");
				return true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retval;
	}

	private static long countInFile(Pair<String, String> p, File sourceFile)  {
		long retval = 0;
		try(Stream<String> stream = Files.lines(sourceFile.toPath()).parallel()) {
			retval = stream
				 .map(s -> Arrays.asList(s.split(",")))
				 .filter(l -> l.contains(p.getLeft()) && l.contains(p.getRight()))
				 .count();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retval;
	}

	private static List<Pair<String,String>> allPairs(List<String> l) {
		List<Pair<String, String>> retval = new ArrayList<Pair<String, String>>();
		for(int i = 0; i<l.size(); i++) {
			for(int j = 0; j<l.size(); j++) {
				if(i!=j) {
					retval.add(new Pair<String, String>(l.get(i), l.get(j)));
				}
			}
		}
		return retval;
	}
}
