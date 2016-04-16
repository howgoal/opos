package com.example.opos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileIO {
	String readData = "";
 
	public void writeFile(String fileName) {
		try {
			FileWriter fw = new FileWriter("/sdcard/" + fileName, false);
			BufferedWriter bw = new BufferedWriter(fw); // �NBufferedWeiter�PFileWrite���󰵳s��
			bw.write("Hello, Sih! Hello, Android!");
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String readFile(String fileName) {
		try {
			FileReader fr = new FileReader("/sdcard/" + fileName);
			BufferedReader br = new BufferedReader(fr);
			while (br.ready()) {
				readData += br.readLine() + " ";
			}
			fr.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return readData;
	}
}
