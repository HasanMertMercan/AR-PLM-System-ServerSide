package com.middleLayer;

import java.util.ArrayList;

import com.optimisation.City;
import com.optimisation.GA;
import com.optimisation.Population;
import com.optimisation.TourManager;

public class OptimiseRootInsidePlant {

	private ArrayList<MachineProperties> machinesWithError = new ArrayList<MachineProperties>();
	private ArrayList<City> optimisedRootMachineList = new ArrayList<City>();
	private City[] city;
	
	//Run this method twice; one time for Green error state, one time for Red error state
	public OptimiseRootInsidePlant(String factoryId, int errorState) 
	{
		//TODO
			
		//Makinelerin error statei "assignRandomError"den degil, ARClienttan gelen makine error Stateleri ile doldurulacak
		//ARClient tarafinda kullaniciya bu sorunu cozmek isteyip istemedigi sorulacak
		AssignRandomError assignRandomError = new AssignRandomError(factoryId);
		machinesWithError = assignRandomError.getMachineErrorList();
		city = new City[machinesWithError.size()];

        // Create and add our cities
		for(int i = 0; i < machinesWithError.size(); i++) 
		{
			if(Integer.parseInt(machinesWithError.get(i).getErrorState()) == errorState) 
			{
				city[i] = new City(machinesWithError.get(i).getId(), Integer.parseInt(machinesWithError.get(i).getxAxis()), Integer.parseInt(machinesWithError.get(i).getyAxis()), machinesWithError.get(i).getErrorState());
				TourManager.addCity(city[i]);
			}
			
		}
		
        // Initialize population
        Population pop = new Population(50, true);
        System.out.println("Initial distance: " + pop.getFittest().getDistance());

        // Evolve population for 100 generations
        pop = GA.evolvePopulation(pop);
        for (int i = 0; i < 100; i++) {
            pop = GA.evolvePopulation(pop);
        }

        // Print final results
        System.out.println("Finished");
        System.out.println("Final distance: " + pop.getFittest().getDistance());
        System.out.println("Solution:");
        System.out.println(pop.getFittest());
        
        for(int i = 0; i < TourManager.numberOfCities(); i++) 
        {
        	optimisedRootMachineList.add(pop.getFittest().getCity(i));
        }
	        
	}
	
	public ArrayList<City> getOptimisedRootMachineList() 
	{
		return optimisedRootMachineList;
	}
}
