package com.planon.mdb;

import java.util.Arrays;

public class demo {

	public static void main(String[] args) {

		// initializing unsorted float array
		String fArr[] = { "1.10.1", "1.2", "1" };

		// let us print all the elements available in list
		for (String number : fArr) {
			System.out.println("Number = " + number);
		}

		// sorting array
		Arrays.sort(fArr);

		// let us print all the elements available in list
		System.out.println("The sorted float array is:");
		for (String number : fArr) {
			System.out.println("Number = " + number);
		}

	}

}
